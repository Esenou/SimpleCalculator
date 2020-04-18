public class Model {
    private Viewer viewer;
    public String temp;
    private Polski polski;
    private ConvertToRome convert;
    private int val;
    private boolean flag = false;
    public Model(Viewer viewer) {
        this.viewer = viewer;
        temp = "";
        polski = new Polski();
        convert = new ConvertToRome();
    }

    public void doAction(String value) {   
          
        for(int i = 0; i < value.length(); i++) {
             if(Character.isDigit(value.charAt(i))) {
                 flag = true;
                 break;
             }               
        }

        if(flag) {
             try {                                                                    
                 temp = String.valueOf(polski.decide(value));
             } catch(Exception ioe){
                 System.out.println("Не правильно вели данные!");
             }

        } else {
             try {                                           
                 polski.decide(convert.romanToArab(value)); 
                 temp = convert.arabicToRoman((int)polski.decide(convert.romanToArab(value)));      
             } catch(Exception ioe){
                 System.out.println("Не правильно вели данные!");
             }
        }
  
        viewer.update(temp);                    
    }    

}