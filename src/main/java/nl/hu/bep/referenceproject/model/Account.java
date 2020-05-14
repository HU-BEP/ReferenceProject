package nl.hu.bep.referenceproject.model;

public class Account {
    private String username;
    private String fullname;
    private String address;
    private String avatarBase64;
    private String avatarUploadId;

    public Account() {}

    public Account(String username, String fullname, String address, String avatarBase64) {
        this.username = username;
        this.fullname = fullname;
        this.address = address;
        this.avatarBase64 = avatarBase64;
    }

    public String getUsername() { return username; }
    public String getFullname() { return fullname; }
    public String getAddress() { return address; }
    public String getAvatarBase64() { return avatarBase64; }
    public String getAvatarUploadId() { return avatarUploadId; }

    public void setUsername(String username) { this.username = username; }
    public void setFullname(String fullname) { this.fullname = fullname; }
    public void setAddress(String address) { this.address = address; }
    public void setAvatarBase64(String avatarBase64) { this.avatarBase64 = avatarBase64; }
    public void setAvatarUploadId(String avatarUploadId) { this.avatarUploadId = avatarUploadId; }
}
