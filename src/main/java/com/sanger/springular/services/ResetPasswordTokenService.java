package com.sanger.springular.services;

import java.util.Calendar;
import java.util.Date;

import com.sanger.springular.dto.auth.ResetUserPasswordDto;
import com.sanger.springular.error.exceptions.PasswordNotMismatch;
import com.sanger.springular.error.exceptions.UserNotFoundException;
import com.sanger.springular.model.PasswordResetToken;
import com.sanger.springular.model.UserEntity;
import com.sanger.springular.repository.PasswordResetTokenRepository;
import com.sanger.springular.repository.UserEntityRepository;
import com.sanger.springular.services.base.BaseService;
import com.sanger.springular.utils.mail.EmailService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;

@Service
@RequiredArgsConstructor
public class ResetPasswordTokenService extends BaseService<PasswordResetToken, Long, PasswordResetTokenRepository> {

    private final EmailService emailService;

    private final UserEntityRepository userEntityRepository;

    private final PasswordEncoder passwordEncoder;

    public static final String TOKEN_INVALID = "invalidToken";
    public static final String TOKEN_EXPIRED = "expired";
    public static final String TOKEN_VALID = "valid";

    /**
     * sirve para generar el mail que se le envia al usuario resetear la contraseña
     * 
     * @param email
     * @param urlRedirect
     * @return
     */
    public void sendEmailResetToken(String email, String urlRedirect) {
        UserEntity user = userEntityRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException());
        String token = createVerificationTokenForUser(user);
        emailService.sendMail(user.getEmail(), user.getFullName(), "Welcome " + user.getFullName()
                + " follow this link to reset your password " + urlRedirect + "?tokenuid=" + token);
    }

    public String createVerificationTokenForUser(UserEntity user) {
        String token = RandomString.make(45);
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setUser(user);
        passwordResetToken.setToken(token);

        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();

        passwordResetToken.setExpiryDate(dt);
        this.save(passwordResetToken);

        return token;
    }

    public String validateVerificationToken(ResetUserPasswordDto resetPasswordTokenDto, String token) {
        final PasswordResetToken verificationToken = this.repository.findByToken(token);
        if (verificationToken == null) {
            return TOKEN_INVALID;
        }
        final UserEntity user = verificationToken.getUser();
        final Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            this.repository.delete(verificationToken);
            return TOKEN_EXPIRED;
        } else if (resetPasswordTokenDto.getPassword().equals(resetPasswordTokenDto.getPassword2())) {
            user.setEnabled(true);
            user.setPassword(passwordEncoder.encode(resetPasswordTokenDto.getPassword()));
            userEntityRepository.save(user);
            this.repository.delete(verificationToken);
            return TOKEN_VALID;
        } else {
            throw new PasswordNotMismatch();
        }

    }

}
