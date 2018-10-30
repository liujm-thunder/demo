package com.appchina.collect.test.mybatis.model;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

public class DeveloperInfo implements Serializable {

    private static final long serialVersionUID = -8313403368910515903L;

    private Integer userId;

    private Integer type;

    private String name;

    private String idcard;

    private String photo;

    private String photoCard;

    private String cellphone;

    private String email;

    private String contacts;

    private String legalPerson;

    private String country;

    private String province;

    private String city;

    private String area;

    private String address;

    private String audit;

    private Integer license;

    private File photoFile;
    
    private Date ctime;
    
    private Integer status;
    
    private String refuseReason;
    
    private String qq;
    
    private String zipcode;
    
    private String weibo;
    
    private String commitLetter;

    private String accountNo;//银行卡号码

    private String contactIdCard;

    private String vcode;//实名认证授权码
    //应用汇私钥
    private String yyhPrivateKey;

    //应用汇公钥
    private String yyhPublicKey;

    //开发者公钥
    private String developerPublicKey;

    //企业高级实名认证状态
    private Integer seniorStatus;

    private Integer certifyType;


    public String getCommitLetter() {
		return commitLetter;
	}

	public void setCommitLetter(String commitLetter) {
		this.commitLetter = commitLetter;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getWeibo() {
		return weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

	public String getAudit() {
		return audit;
	}

	public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone == null ? null : cellphone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getLicense() {
        return license;
    }

    public void setLicense(Integer license) {
        this.license = license;
    }

    public File getPhotoFile() {
        return photoFile;
    }

    public void setPhotoFile(File photoFile) {
        this.photoFile = photoFile;
    }

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Integer getStatus() {
		return status;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setAudit(String audit) {
		this.audit = audit;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

    public String getPhotoCard() {
        return photoCard;
    }

    public void setPhotoCard(String photoCard) {
        this.photoCard = photoCard;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getContactIdCard() {
        return contactIdCard;
    }

    public void setContactIdCard(String contactIdCard) {
        this.contactIdCard = contactIdCard;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public String getYyhPrivateKey() {
        return yyhPrivateKey;
    }

    public void setYyhPrivateKey(String yyhPrivateKey) {
        this.yyhPrivateKey = yyhPrivateKey;
    }

    public String getDeveloperPublicKey() {
        return developerPublicKey;
    }

    public void setDeveloperPublicKey(String developerPublicKey) {
        this.developerPublicKey = developerPublicKey;
    }

    public String getYyhPublicKey() {
        return yyhPublicKey;
    }

    public void setYyhPublicKey(String yyhPublicKey) {
        this.yyhPublicKey = yyhPublicKey;
    }

    public Integer getSeniorStatus() {
        return seniorStatus;
    }

    public void setSeniorStatus(Integer seniorStatus) {
        this.seniorStatus = seniorStatus;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public Integer getCertifyType() {
        return certifyType;
    }

    public void setCertifyType(Integer certifyType) {
        this.certifyType = certifyType;
    }

    @Override
    public String toString() {
        return "DeveloperInfo{" +
                "userId=" + userId +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", idcard='" + idcard + '\'' +
                ", photo='" + photo + '\'' +
                ", photoCard='" + photoCard + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", email='" + email + '\'' +
                ", contacts='" + contacts + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                ", audit='" + audit + '\'' +
                ", license=" + license +
                ", photoFile=" + photoFile +
                ", ctime=" + ctime +
                ", status=" + status +
                ", refuseReason='" + refuseReason + '\'' +
                ", qq='" + qq + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", weibo='" + weibo + '\'' +
                ", commitLetter='" + commitLetter + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", contactIdCard='" + contactIdCard + '\'' +
                ", vcode='" + vcode + '\'' +
                ", yyhPrivateKey='" + yyhPrivateKey + '\'' +
                ", yyhPublicKey='" + yyhPublicKey + '\'' +
                ", developerPublicKey='" + developerPublicKey + '\'' +
                ", seniorStatus=" + seniorStatus +
                '}';
    }
}