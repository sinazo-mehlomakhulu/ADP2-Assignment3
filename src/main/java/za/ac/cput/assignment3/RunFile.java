/*

 * Sinazo Mehlomakhulu(216076498)
 * Assignment3
 * Due date: 09 June 2021
 */
package za.ac.cput.assignment3;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
        

public class RunFile {
    
    ObjectInputStream obj;
    ArrayList <Customer> customer;
    ArrayList <Supplier> supplier;
    ArrayList <Integer> age;
    int x = 0;
    
    public RunFile(){
     customer = new ArrayList();
     supplier = new ArrayList();
     age = new ArrayList();
    }
    
    public void openFile(){
        try{
            obj = new ObjectInputStream(new FileInputStream("stakeholder.ser"));
            System.out.println("--Ser File has been opened--");
            
        }
        catch(IOException ioe){
            System.out.println("Error opening file" + ioe.getMessage());
        }
    }
    public void closeFile(){
        try{
            obj.close();            
        }
        catch(IOException ioe){
            System.out.println("Error closing file" + ioe.getMessage());
            
        }
    }
        public void readObjects(){
            try{
                while(true){
                    Object c = (Stakeholder)obj.readObject();
                    if(c instanceof Customer ){
                         customer.add((Customer) c );
                    }
                    else {
                        supplier.add((Supplier) c );
                    }                 
                }
            }
            catch (EOFException eofe){
                
            }
        catch(ClassNotFoundException ioe){
            System.out.println("Class error reading from file" + ioe);            
        }
            catch (IOException ioe){
                System.out.println("Error reading from the file" + ioe);
            }
        finally{
                closeFile();
                System.out.println("--Ser File has been close--");
            }
                
        }
        
        public void sortArray(){
            Collections.sort(customer, new Comparator<Customer>(){
          @Override
          public int compare(Customer o1, Customer o2){
             return o1.getStHolderId().compareTo(o2.getStHolderId());
          }
          });
         
        Collections.sort(supplier , new Comparator<Supplier>(){
            @Override
            public int compare(Supplier o1, Supplier o2){
                return o1.getName().compareTo(o2.getName());
            }
            });
}
    
 public void CustomerAge() {  
     
 
for(Customer c: customer){
           String []birth = c.getDateOfBirth().split("-");
           int born = Integer.parseInt(birth[0]);
            System.out.println(c.getSurName()+" > "+(2021-born));
            age.add((2021-born));
        }
 }
public int dateOfBirth(Customer o1){
    
        for(Customer c: customer){
            String []birth = c.getDateOfBirth().split("-");
            String born = birth[1];
            if(born.equals("01"))
                c.setDateOfBirth(birth[2]+" Jan "+birth[0]);
            else if(born.equals("02"))
                c.setDateOfBirth(birth[2]+" Feb "+birth[0]);
             else if(born.equals("03"))
                c.setDateOfBirth(birth[2]+" Mar "+birth[0]);
             else if(born.equals("04"))
                c.setDateOfBirth(birth[2]+" Apr "+birth[0]);
             else if(born.equals("05"))
                c.setDateOfBirth(birth[2]+" May "+birth[0]);
             else if(born.equals("06"))
                c.setDateOfBirth(birth[2]+" Jun "+birth[0]);
             else if(born.equals("07"))
                c.setDateOfBirth(birth[2]+" Jul "+birth[0]);
             else if(born.equals("08"))
                c.setDateOfBirth(birth[2]+" Aug "+birth[0]);
             else if(born.equals("09"))
                c.setDateOfBirth(birth[2]+" Sep "+birth[0]);
             else if(born.equals("10"))
                c.setDateOfBirth(birth[2]+" Oct "+birth[0]);
             else if(born.equals("11"))
                c.setDateOfBirth(birth[2]+" Nov "+birth[0]);
             else if(born.equals("12"))
                c.setDateOfBirth(birth[2]+" Dec "+birth[0]);
             else 
                c.setDateOfBirth(birth[2]+" Invalid "+birth[0]);
        }
        return 0;
}
public int canRent(){
    int val = 0;
    for(Customer value:customer){
        if(value.getCanRent()){
            val = val + 1;            
        }
        
    }
        return val;
}
public void writeToCustomerFile(){
        try{
            PrintWriter writer = new PrintWriter(new FileWriter("customerOutFile.txt"));
            writer.write("============================= CUSTOMERS =================================\n");
            writer.write("ID   	Name      	Surname   	Date of birth  	Age  \n");
            writer.write("=========================================================================\n");
            int i = 0;
            int rent = 0;
            for(Customer c: customer){
                if( c.getSurName().length() < 8)
                    writer.write(c.getStHolderId()+ "\t"+ c.getFirstName()+ "\t\t" + c.getSurName()+ "\t\t" + c.getDateOfBirth()+ "\t\t" + age.get(i)+"\n");
                else
                    writer.write(c.getStHolderId()+ "\t"+ c.getFirstName()+ "\t\t" + c.getSurName()+ "\t" + c.getDateOfBirth()+ "\t\t" + age.get(i)+"\n");
                i++;
                if(c.getCanRent())
                    rent++;
            }
            writer.write("Number of customers who can rent: "+rent+"\n");
            writer.write("Number of customers who cannot rent: "+(customer.size()-rent)+"\n");
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void writeSupplierToFile(){
        try{
            PrintWriter writer = new PrintWriter(new FileWriter("supplierOutFile.txt"));
            writer.write("============================== SUPPLIERS ====================================\n");
            writer.write("ID   	Name                	Prod Type	Description    \n");
            writer.write("=============================================================================\n");
            for(int i = 0; i < supplier.size() - 1; i++){
                for(int r = i + 1; r < supplier.size(); r++){
                    if(supplier.get(i).getName().compareTo(customer.get(r).getFirstName()) > 0){
                        Supplier sup = supplier.get(i);
                        supplier.set(i, supplier.get(r)); 
                        supplier.set(r, sup);
                    }
                }
            }
             for(Supplier s: supplier){
               if( s.getName().length() < 8)
                        writer.write(s.getStHolderId()+ "\t"+ s.getName()+ "\t\t\t" +s.getProductType()+ "\t\t\t" + s.getProductDescription()+"\n");
                    else
                        writer.write(s.getStHolderId()+ "\t"+ s.getName()+ "\t\t" + s.getProductType()+ "\t\t" + s.getProductDescription()+"\n");
            }
             writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
       public static void main(String args[])  {
       RunFile obj = new RunFile();
        obj.openFile();
        obj.writeToCustomerFile();
        obj.writeSupplierToFile();
    }//end main    
    }
