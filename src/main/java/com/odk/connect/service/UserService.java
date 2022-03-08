package com.odk.connect.service;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.web.multipart.MultipartFile;

import com.odk.connect.enumeration.Role;
import com.odk.connect.exception.model.EmailExistException;
import com.odk.connect.exception.model.EmailNotFoundException;
import com.odk.connect.exception.model.MotDePasseException;
import com.odk.connect.exception.model.NotAnImageFileException;
import com.odk.connect.exception.model.UserNotFoundException;
import com.odk.connect.exception.model.UsernameExistException;
import com.odk.connect.model.Alumni;
import com.odk.connect.model.ChangerMotDePasseUser;
import com.odk.connect.model.LignePromotion;
import com.odk.connect.model.User;

public interface UserService {
	User registerUser(String prenom, String nom, String login, String email, String adresse, String telephone)
			throws UserNotFoundException, UsernameExistException, EmailExistException, MessagingException;

	Alumni registerAlum(String login, String email, String adresse, String telephone,String profession)
			throws UserNotFoundException, UsernameExistException, EmailExistException, MessagingException,
			EmailNotFoundException;

	List<User> getUsers();
	List<User>getUsersByRole(String role) throws UserNotFoundException;

	User findUserByUsername(String Username);

	User findUserByEmail(String email);

	User addNewUser(String prenom, String nom, String login, String email, String adresse, String telephone,
			String role,boolean isActive, boolean isNotLocked, MultipartFile profileImage)
			throws UserNotFoundException, EmailExistException, UsernameExistException, IOException,
			NotAnImageFileException;

	Alumni addNewAlumni(String prenom, String nom, String login, String email, String adresse, String telephone, 
			String profession, String role, boolean isActive, boolean isNotLocked, MultipartFile profileImage)
			throws UserNotFoundException, EmailExistException, UsernameExistException, IOException,
			NotAnImageFileException;

	User updateUser(String currentUsername, String newFirstName, String newLastName, String newUsername,
			String newEmail, String adresse, String telephone, String role, boolean isActive, boolean isNotLocked,
			MultipartFile profileImage) throws UserNotFoundException, EmailExistException, UsernameExistException,
			IOException, NotAnImageFileException;

	void deleteUser(String login) throws IOException, UsernameExistException;

	void resetPassword(String email) throws MessagingException, EmailNotFoundException;

	User updateProfileImage(String username, MultipartFile profileImage) throws EmailExistException,
			UsernameExistException, UserNotFoundException, IOException, NotAnImageFileException;

	User ChangerMotDePasse(ChangerMotDePasseUser mdp) throws MotDePasseException;

}