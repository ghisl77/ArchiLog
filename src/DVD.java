public class DVD extends Document{
    private boolean adulte;
    public DVD(int num, String titre,boolean adulte){
        super(num,titre);
        this.adulte = adulte;
    }
}
