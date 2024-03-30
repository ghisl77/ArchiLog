package document;

import java.time.LocalDateTime;
import java.util.Date;

public class DVD extends Document{
    private boolean adulte;
    public DVD(int num, String titre,boolean adulte){
        super(num,titre);
        this.adulte = adulte;
    }
    public boolean verifieAge(Date dateNais){
        if(adulte==true){
            Date currentDate = new Date();
            long diffInMillis = currentDate.getTime() - dateNais.getTime();
            long ageInMillis = diffInMillis / (1000L * 60 * 60 * 24 * 365);
            int age = (int) ageInMillis;
            if(age>16){
                return true;
            }
            else
                return false;
        }
        return true;
    }
}
