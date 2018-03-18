
public class User {
    
    
    
    
     
      private String id  ;
      private String first_name;
      private String last_name;
      private String user_name;
      private String email;
      private String hash;
      private String status;
      private String password;

      
      
      
      
     public String get_id() {
        return  id;
    }
      
      public void set_id(String id) {
        this.id = id;
    }
    

     public String get_first_name() {
        return first_name ;
    }
    
    public void set_first_name(String first_name) {
        this.first_name = first_name;
    }
    public String get_last_name() {
        return last_name ;
    }
    
    public void set_last_name(String last_name) {
        this.last_name = last_name;
    }
   
      public String get_user_name() {
        return user_name ;
    }
    
    public void set_user_name(String user_name) {
        this.user_name = user_name;
    }
    
    
    
    
   
   
    
    
    public String get_hash() {
        return hash;
    }

    public void set_hash(String hash) {
        this.hash = hash;
    }
    
    
    
    
    
    
     public String get_email() {
            return email;
    }
    
    
     public void set_email(String email) {
        this.email = email;
    }
   

    
      
    
     public String get_status() {
        return status ;
    }
    
      
     public void set_status(String status) {
        this.status = status;
    }
    
    
    
     public String get_password() {
        return password ;
    }
    
    public void set_password(String password) {
        this.password = password;
    }

    
}
