package service.custom.impl;
import entity.UserEntity;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import javafx.collections.ObservableList;
import model.User;
import model.UserLogin;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.UserDao;
import service.custom.UserService;
import util.DaoType;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import static jakarta.mail.Session.getInstance;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl userServiceImplinstance;
    private UserServiceImpl(){
    }
    public static UserServiceImpl getUserServiceImplinstance(){
        return (userServiceImplinstance==null)? userServiceImplinstance = new UserServiceImpl(): userServiceImplinstance;
    }

    @Override
    public int userLogin(UserLogin login) {
        UserDao repository = DaoFactory.getInstance().getDao(DaoType.USER);
        UserEntity userEntity = repository.findByEmail(login.getUsername());
        boolean isValidLogin = userEntity != null && BCrypt.checkpw(login.getPassword(),userEntity.getPassword());
        if (isValidLogin) {
            boolean admin = userEntity.getRole().equals("Admin");
            if (admin) {
                return -1;
            } else {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public boolean addUser(User user) {
        UserEntity entity = new ModelMapper().map(user, UserEntity.class);
        UserDao repository = DaoFactory.getInstance().getDao(DaoType.USER);
        return repository.addUser(entity);
    }

    @Override
    public ObservableList<UserEntity> getAllUsers() {
        UserDao repository = DaoFactory.getInstance().getDao(DaoType.USER);
        return repository.getAllUsers();
    }

    @Override
    public boolean updateUser(UserEntity userEntity) {
        UserDao repository = DaoFactory.getInstance().getDao(DaoType.USER);
        return repository.updateUser(userEntity);
    }

    @Override
    public boolean deleteUser(long id) {
        UserDao repository = DaoFactory.getInstance().getDao(DaoType.USER);
        return repository.deleteUser(id);
    }

    @Override
    public User searchUser(long id) {
        UserDao repository = DaoFactory.getInstance().getDao(DaoType.USER);
        UserEntity userEntity = repository.findUserById(id);
        if (userEntity != null){
            return new ModelMapper().map(userEntity, User.class);
        } else {
            return null;
        }
    }

    @Override
    public boolean isValidUser(String email) {
        UserDao repository = DaoFactory.getInstance().getDao(DaoType.USER);
        UserEntity userEntity = repository.findByEmail(email);
        if (userEntity != null){
            return true;
        } else {
            return false;
        }
    }

    private Integer otp;
    public boolean sendOtp(String email) {
        otp = ThreadLocalRandom.current().nextInt(1000, 9999);

        String host = "smtp.gmail.com";
        String from = "clothifystore119@gmail.com";
        String password = "fdty prkz qsqh dbow";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");

        Session session = getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Your OTP Code");
            message.setText("Your OTP code is: " + otp);

            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean validateOtp(Integer enteredOtp) {
        return otp.equals(enteredOtp);
    }
}
