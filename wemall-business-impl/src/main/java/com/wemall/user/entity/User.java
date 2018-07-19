package com.wemall.user.entity;

import java.io.Serializable;
import java.util.List;

import com.wemall.role.entity.Role;

public class User implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.id
     *
     * @mbggenerated
     */
    private Long id;
    
    private List<Role> roles;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.user_account
     *
     * @mbggenerated
     */
    private String userAccount;



	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.password
     *
     * @mbggenerated
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.user_name
     *
     * @mbggenerated
     */
    private String userName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.user_phone
     *
     * @mbggenerated
     */
    private String userPhone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.user_email
     *
     * @mbggenerated
     */
    private String userEmail;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.user_sex
     *
     * @mbggenerated
     */
    private Integer userSex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.user_birthday
     *
     * @mbggenerated
     */
    private String userBirthday;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.register_time
     *
     * @mbggenerated
     */
    private String registerTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.department_key
     *
     * @mbggenerated
     */
    private String departmentKey;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.salt
     *
     * @mbggenerated
     */
    private String salt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_user
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.id
     *
     * @return the value of sys_user.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.id
     *
     * @param id the value for sys_user.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.user_account
     *
     * @return the value of sys_user.user_account
     *
     * @mbggenerated
     */
    public String getUserAccount() {
        return userAccount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.user_account
     *
     * @param userAccount the value for sys_user.user_account
     *
     * @mbggenerated
     */
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.password
     *
     * @return the value of sys_user.password
     *
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.password
     *
     * @param password the value for sys_user.password
     *
     * @mbggenerated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.user_name
     *
     * @return the value of sys_user.user_name
     *
     * @mbggenerated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.user_name
     *
     * @param userName the value for sys_user.user_name
     *
     * @mbggenerated
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.user_phone
     *
     * @return the value of sys_user.user_phone
     *
     * @mbggenerated
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.user_phone
     *
     * @param userPhone the value for sys_user.user_phone
     *
     * @mbggenerated
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.user_email
     *
     * @return the value of sys_user.user_email
     *
     * @mbggenerated
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.user_email
     *
     * @param userEmail the value for sys_user.user_email
     *
     * @mbggenerated
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.user_sex
     *
     * @return the value of sys_user.user_sex
     *
     * @mbggenerated
     */
    public Integer getUserSex() {
        return userSex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.user_sex
     *
     * @param userSex the value for sys_user.user_sex
     *
     * @mbggenerated
     */
    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.user_birthday
     *
     * @return the value of sys_user.user_birthday
     *
     * @mbggenerated
     */
    public String getUserBirthday() {
        return userBirthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.user_birthday
     *
     * @param userBirthday the value for sys_user.user_birthday
     *
     * @mbggenerated
     */
    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday == null ? null : userBirthday.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.register_time
     *
     * @return the value of sys_user.register_time
     *
     * @mbggenerated
     */
    public String getRegisterTime() {
        return registerTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.register_time
     *
     * @param registerTime the value for sys_user.register_time
     *
     * @mbggenerated
     */
    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime == null ? null : registerTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.department_key
     *
     * @return the value of sys_user.department_key
     *
     * @mbggenerated
     */
    public String getDepartmentKey() {
        return departmentKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.department_key
     *
     * @param departmentKey the value for sys_user.department_key
     *
     * @mbggenerated
     */
    public void setDepartmentKey(String departmentKey) {
        this.departmentKey = departmentKey == null ? null : departmentKey.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.salt
     *
     * @return the value of sys_user.salt
     *
     * @mbggenerated
     */
    public String getSalt() {
        return salt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.salt
     *
     * @param salt the value for sys_user.salt
     *
     * @mbggenerated
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbggenerated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserAccount() == null ? other.getUserAccount() == null : this.getUserAccount().equals(other.getUserAccount()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getUserPhone() == null ? other.getUserPhone() == null : this.getUserPhone().equals(other.getUserPhone()))
            && (this.getUserEmail() == null ? other.getUserEmail() == null : this.getUserEmail().equals(other.getUserEmail()))
            && (this.getUserSex() == null ? other.getUserSex() == null : this.getUserSex().equals(other.getUserSex()))
            && (this.getUserBirthday() == null ? other.getUserBirthday() == null : this.getUserBirthday().equals(other.getUserBirthday()))
            && (this.getRegisterTime() == null ? other.getRegisterTime() == null : this.getRegisterTime().equals(other.getRegisterTime()))
            && (this.getDepartmentKey() == null ? other.getDepartmentKey() == null : this.getDepartmentKey().equals(other.getDepartmentKey()))
            && (this.getSalt() == null ? other.getSalt() == null : this.getSalt().equals(other.getSalt()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserAccount() == null) ? 0 : getUserAccount().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getUserPhone() == null) ? 0 : getUserPhone().hashCode());
        result = prime * result + ((getUserEmail() == null) ? 0 : getUserEmail().hashCode());
        result = prime * result + ((getUserSex() == null) ? 0 : getUserSex().hashCode());
        result = prime * result + ((getUserBirthday() == null) ? 0 : getUserBirthday().hashCode());
        result = prime * result + ((getRegisterTime() == null) ? 0 : getRegisterTime().hashCode());
        result = prime * result + ((getDepartmentKey() == null) ? 0 : getDepartmentKey().hashCode());
        result = prime * result + ((getSalt() == null) ? 0 : getSalt().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userAccount=").append(userAccount);
        sb.append(", password=").append(password);
        sb.append(", userName=").append(userName);
        sb.append(", userPhone=").append(userPhone);
        sb.append(", userEmail=").append(userEmail);
        sb.append(", userSex=").append(userSex);
        sb.append(", userBirthday=").append(userBirthday);
        sb.append(", registerTime=").append(registerTime);
        sb.append(", departmentKey=").append(departmentKey);
        sb.append(", salt=").append(salt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}