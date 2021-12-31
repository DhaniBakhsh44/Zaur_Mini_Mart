// /*
//  * To change this template, choose Tools | Templates
//  * and open the template in the editor.
//  */
// package Frames;

// import BeanClasses.AmountDetailBean;
// import BeanClasses.ProductsBean;
// import DBmanager.DBManager;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.print.*;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dhani
 */
public class UserFrame extends javax.swing.JFrame {

    /**
     * Creates new form UserFrame
     */
    
    ///This user Id user for Transaction
    private int user_id;
    // these three static variable use for Total_Amount cell of Tabel
    private  int qty_Total_Amount=0;
    private  int row=0;
    
    //this variable user name is user of bill
    private String user_name;
    private static int items=0;
   
  DateFormat dateFormat = new SimpleDateFormat("YYY-MM-dd");
   Date dat = new Date();
   String date=dateFormat.format(dat);
  
   ///for time
   DateFormat dftime = new SimpleDateFormat("HH:mm:ss");
   Calendar caltime = Calendar.getInstance();
   String time=dftime.format(caltime.getTime());
           
    
    public UserFrame(){
       //SET JFRAME FULL SCREEN SIZE
       setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();
//  tableSelection();
        //setTotalAmount();
    
    }
    
       public UserFrame(int user_id,String user_name) {
       //SET JFRAME FULL SCREEN SIZE
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents(); 
        this.user_name=user_name;
        userNameLabel.setText(user_name);
        this.user_id=user_id;
        dateLabel.setText(""+date);
        
  //     tableSelection();
      // setTotalAmount();
    }
       
  // This Method is user for get Refund Iitems and insert in addReundItemQty(,,)     
//private void reFundsItem(){
//        int rows=userTable.getRowCount();    
//        for(int row = 0; row <rows; row++) {
//         int item_no=(int)userTable.getValueAt(row, 0);     
//         String prod_name = (String) userTable.getValueAt(row, 1);
//         String barcode = (String) userTable.getValueAt(row, 2);
//         int qty =Integer.parseInt(userTable.getValueAt(row, 3).toString());
//         int unit_price =Integer.parseInt(userTable.getValueAt(row, 4).toString());
//         int total=unit_price*qty; 
//         addRefundItemQty(barcode,qty);
//       }
//        
//      JOptionPane.showMessageDialog(null,"Item Refund Succesfully..");
//       }
// this method is use for add qty of refund item
       int updateQty=0;
private void addRefundItemQty(String barcode,int qty){
    
       try{
           Vector v=DBManager.getDataByBarcode(barcode);
             
           for(int i=0; i<v.size(); i++){
            ProductsBean bean=(ProductsBean)v.elementAt(i);
            int totalQty=bean.getProd_qty();
            int remQty=totalQty+qty;        
            updateQty=DBManager.itemUpdate(barcode,remQty);  
            int refund_amount=Integer.parseInt(totalAmountTextField.getText());
       getUserAmount( user_id ,refund_amount,date);
           }
           
        
    }catch(Exception e){
        e.printStackTrace();
        JOptionPane.showMessageDialog(null,"UpdateQtyException..."+e);
    }
}
// this method is use get amount of user 
private void getUserAmount(int user_id,int refund_amount ,String date){
    try{
        Vector v=DBManager.getAmountDetail(user_id, date);
        for(int i=0; i<v.size(); i++){
            AmountDetailBean bean=(AmountDetailBean)v.elementAt(i);
            int amount=bean.getUser_selling_amount();
            int rem_amount=amount-refund_amount;
            updateAmounttDetail(user_id,rem_amount,date);
          
        }
        
    }catch(Exception e){
        e.printStackTrace();
        JOptionPane.showMessageDialog(null,""+e);
    }
}
     
 //This method of insert Every item in Transaction Table items which is saled 
  private void addTransaction(int user_id,String prod_name,String barcode,int qty,int unit_price,int total_price){
              
        try{
            int rows =DBManager.addTransaction(user_id,prod_name,barcode,qty,unit_price,total_price);
            
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error " +e);
        }
  }


 private void getDataByBarcode(String barcode){
   
        try{
           
            Vector v=DBManager.getDataByBarcode(barcode);
            // below code get data by prodid 
           if(v.isEmpty()){
               double prod_id=Double.parseDouble(barcode);
               int pro_id=(int)prod_id;
              // JOptionPane.showMessageDialog(null,"prod id="+a);
               v=DBManager.getDataByProdId(pro_id);
           }
            
           
           if(v.isEmpty()){
               JOptionPane.showMessageDialog(this,"Item Not Found...");
               return;
           }
            DefaultTableModel dm = (DefaultTableModel)userTable.getModel();
            for(int i=0; i<v.size(); i++){
               ProductsBean bean=(ProductsBean)v.elementAt(i);
               int prod_id=bean.getProd_id();
               String prod_name=bean.getProd_name();
               String bar_code=bean.getProd_barcode();
              int price=bean.getProd_price();
              items++;
               dm.addRow(new Object[]{items,prod_id,prod_name,bar_code,""+1,price,price*1}); 
            }
     //       totalAmountTextField.setText(""+total_amount); 
  
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Exception"+e);
        }
        
   
}
        
private void tableSelection(){
    final ListSelectionModel model=userTable.getSelectionModel();
    model.addListSelectionListener(new ListSelectionListener(){

        @Override
        public void valueChanged(ListSelectionEvent e) {
        
     //       model.
            if(! model.isSelectionEmpty()){
            int row=model.getMinSelectionIndex();
            JOptionPane.showMessageDialog(null,"Row..."+row);

        }
        }
    
    
    }); 
    }
    
    /// when coustomer take a two or more same item then user just update the quantity
       
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        userNameLabel = new javax.swing.JLabel();
        userImgLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        barcodeTextField = new javax.swing.JTextField();
        SearchButton = new javax.swing.JButton();
        clearTableButton = new javax.swing.JButton();
        printButton = new javax.swing.JButton();
        refundItemButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        totalAmountTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cashTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ChangeTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setForeground(new java.awt.Color(0, 102, 255));

        userTable.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No Items ", "Prod Id", "Description", "Barcode", "Quantity", "Price", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        userTable.setCellSelectionEnabled(true);
        userTable.setRowHeight(25);
        userTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userTableMouseClicked(evt);
            }
        });
        userTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                userTableKeyReleased(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                userTableKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(userTable);

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("User:");

        userNameLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        userNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        userNameLabel.setText("Name");

        userImgLabel.setIcon(new javax.swing.ImageIcon("Picture\\1Users-Administrator-icon.png")); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Date");

        dateLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dateLabel.setForeground(new java.awt.Color(255, 255, 255));
        dateLabel.setText("jLabel9");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(1, 1, 1)
                        .addComponent(userNameLabel)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateLabel))))
                    .addComponent(userImgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 1075, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(userImgLabel)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(42, 42, 42))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(userNameLabel)
                            .addComponent(jLabel8)
                            .addComponent(dateLabel))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Barcode");

        barcodeTextField.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        barcodeTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        barcodeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barcodeTextFieldActionPerformed(evt);
            }
        });

        SearchButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        SearchButton.setIcon(new javax.swing.ImageIcon("Picture\\Search-icon.png")); // NOI18N
        SearchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SearchButtonMouseEntered(evt);
            }
        });
        SearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchButtonActionPerformed(evt);
            }
        });

        clearTableButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        clearTableButton.setIcon(new javax.swing.ImageIcon("Picture\\Ecommerce-Clear-Shopping-Cart-icon.png")); // NOI18N
        clearTableButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                clearTableButtonMouseEntered(evt);
            }
        });
        clearTableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearTableButtonActionPerformed(evt);
            }
        });

        printButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        printButton.setIcon(new javax.swing.ImageIcon("Picture\\print-icon.png")); // NOI18N
        printButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                printButtonMouseEntered(evt);
            }
        });
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        refundItemButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        refundItemButton.setIcon(new javax.swing.ImageIcon("Picture\\Ecommerce-Return-Purchase-icon.png")); // NOI18N
        refundItemButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                refundItemButtonMouseEntered(evt);
            }
        });
        refundItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refundItemButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Total Ammount");

        totalAmountTextField.setEditable(false);
        totalAmountTextField.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Cash");

        cashTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cashTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cashTextFieldActionPerformed(evt);
            }
        });
        cashTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cashTextFieldKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cashTextFieldKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Change");

        ChangeTextField.setEditable(false);
        ChangeTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(barcodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cashTextField)
                                    .addComponent(totalAmountTextField)
                                    .addComponent(ChangeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 703, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearTableButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(printButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refundItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(290, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(printButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(SearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(barcodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clearTableButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(refundItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalAmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cashTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(ChangeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(161, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void SearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchButtonActionPerformed

     String barcode=barcodeTextField.getText();
if(barcode.equals("")){JOptionPane.showMessageDialog(null,"Kindly Insert Barcode in TextFiled....");return;}
        getDataByBarcode(barcode);
        barcodeTextField.setText("");
        setTotalAmountInTotalAmountTextFiled();
    }//GEN-LAST:event_SearchButtonActionPerformed

    private void refundItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refundItemButtonActionPerformed
int confrim=JOptionPane.showConfirmDialog(null,"Are You Sure For Refund The Items...?","Confrim",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);

if(confrim==0){
int rows=userTable.getRowCount();  
        if(rows==0)return; 
        try{
        for(int i=0; i<rows; i++){
  String barcode=(String)userTable.getValueAt(i,3);
  String qty=(String)userTable.getValueAt(i, 4);
  int qtyy=Integer.parseInt(qty);
  //JOptionPane.showMessageDialog(null,"Barcode="+barcode +"QTY="+qtyy);
addRefundItemQty(barcode,qtyy);
        }
        }catch(Exception e){ e.printStackTrace();JOptionPane.showMessageDialog(null,""+e);}
        
        if(updateQty>=0){
               JOptionPane.showMessageDialog(null,"Item Refunds Successfully.....");
           }
       
    }//GEN-LAST:event_refundItemButtonActionPerformed
    }
    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
String cash=cashTextField.getText();
        if(cash.equals("")){JOptionPane.showMessageDialog(null,"Kindly Insert Cash Amount...");return;}
  PrinterJob pj = PrinterJob.getPrinterJob();        
         pj.setPrintable(new BillPrintable(),getPageFormat(pj));
      
         try{
              pj.print();
         }
          catch (PrinterException ex) {
                  ex.printStackTrace();
         
         }
//   Bleow code for Transaction update remaing aty in products       
      
        int rows=userTable.getRowCount();    
      for (int row = 0; row <rows; row++) {
       int item_no=(int)userTable.getValueAt(row, 0);   
       int prod_id=(int)userTable.getValueAt(row, 1);
       String prod_name = (String) userTable.getValueAt(row, 2);
       String barcode = (String) userTable.getValueAt(row, 3);
        int qty =Integer.parseInt(userTable.getValueAt(row, 4).toString());
        int unit_price =Integer.parseInt(userTable.getValueAt(row, 5).toString());
        int total=unit_price*qty; 
       // JOptionPane.showMessageDialog(null,"QTY"+qty);
        updateQty(prod_id,barcode,qty);
        addTransaction( user_id,prod_name,barcode,qty,unit_price,total);
        
        items=0;  
      }   
getAmountDetail(user_id,date);      
    }//GEN-LAST:event_printButtonActionPerformed

   private void getAmountDetail(int user_id,String date){
      try{
       Vector v=DBManager.getAmountDetail(user_id,date);
       for(int i=0; i<v.size(); i++){
           AmountDetailBean bean=(AmountDetailBean)v.elementAt(i);
            int selling_amount=bean.getUser_selling_amount();
            int total_amount=Integer.parseInt(totalAmountTextField.getText());
            int amount=selling_amount+total_amount;
            updateAmounttDetail(user_id,amount,date);
           }
       if(v.isEmpty()){
 int user_selling_amount=Integer.parseInt(totalAmountTextField.getText());
addAmountDetail(user_id, user_selling_amount,date);
           
  }
       
      }catch(Exception e){
          e.printStackTrace();
          JOptionPane.showMessageDialog(null,""+e);
      }
   }
private void addAmountDetail(int user_id,int user_selling_amount,String date){
    try{
        
int row=DBManager.addAmountDetail(user_id, user_selling_amount);
    }catch(Exception e){
        e.printStackTrace();
        JOptionPane.showMessageDialog(null,""+e);
    }
       
}    
    
private void updateAmounttDetail(int user_id,int amount ,String date){
    try{
        int row=DBManager.updateAmountDetail(user_id,amount, date);
   
    }catch(Exception e){
        e.printStackTrace();
        JOptionPane.showMessageDialog(null,""+e);
    }
}



    // This Minus the Qty which is saled 
    private void updateQty(int prod_id,String barcode,int qty){
    
       try{
 
           Vector v=DBManager.getDataByBarcode(barcode);
           if(!v.isEmpty()){
               v=DBManager.getDataByProdId(prod_id);
        for(int i=0; i<v.size(); i++){
            ProductsBean bean=(ProductsBean)v.elementAt(i);
            int totalQty=bean.getProd_qty();
            int remQty=(totalQty-qty);
                  int updateQty=DBManager.itemUpdate(barcode,remQty);
           
        }
           }
           
             if(v.isEmpty()){
             v=DBManager.getDataByProdId(prod_id);
        for(int i=0; i<v.size(); i++){
            ProductsBean bean=(ProductsBean)v.elementAt(i);
            int totalQty=bean.getProd_qty();
            int remQty=(totalQty-qty);
            String pro_name=bean.getProd_name();
           int p_price=bean.getProd_price();
          int updateQty=DBManager.updateItemsByProId(prod_id,pro_name, remQty, p_price);
           
        }
           }
           
        
    }catch(Exception e){
        e.printStackTrace();     
        JOptionPane.showMessageDialog(null,"UpdateQtyException..."+e);
    }
}
    
    
    //// Printing Code
     public PageFormat getPageFormat(PrinterJob pj)
{
    
    PageFormat pf = pj.defaultPage();
    Paper paper = pf.getPaper();    

    double middleHeight =8.0;  
    double headerHeight = 2.0;                  
    double footerHeight = 2.0;                  
    double width = convert_CM_To_PPI(8);      //printer know only point per inch.default value is 72ppi
    double height = convert_CM_To_PPI(headerHeight+middleHeight+footerHeight); 
    paper.setSize(width, height);
    paper.setImageableArea(                    
        0,
        10,
        width,            
        height - convert_CM_To_PPI(1)
    );   //define boarder size    after that print area width is about 180 points
            
    pf.setOrientation(PageFormat.PORTRAIT);           //select orientation portrait or landscape but for this time portrait
    pf.setPaper(paper);    

    return pf;
}
    
    protected static double convert_CM_To_PPI(double cm) {            
	        return toPPI(cm * 0.393600787);            
}
 
protected static double toPPI(double inch) {            
	        return inch * 72d;            
}

public class BillPrintable implements Printable {
       
  public int print(Graphics graphics, PageFormat pageFormat,int pageIndex) 
  throws PrinterException 
  {    
 
         int result = NO_SUCH_PAGE;    
        if (pageIndex == 0) {                    
        
            Graphics2D g2d = (Graphics2D) graphics;                    

            double width = pageFormat.getImageableWidth();                    
           
            g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY()); 

            FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
        //    int idLength=metrics.stringWidth("000000");
            //int idLength=metrics.stringWidth("00");
            int idLength=metrics.stringWidth("000");
            int amtLength=metrics.stringWidth("000000");
            int qtyLength=metrics.stringWidth("00000");
            int priceLength=metrics.stringWidth("000000");
            int prodLength=(int)width - idLength - amtLength - qtyLength - priceLength-17;

             int productPosition = 0;
            int discountPosition= prodLength+5;
            int pricePosition = discountPosition +idLength+10;
            int qtyPosition=pricePosition + priceLength + 4;
            int amtPosition=qtyPosition + qtyLength;
            
            
              
        try{
            /*Draw Header*/
            int y=20;
            int yShift = 10;
            int headerRectHeight=15;
            int headerRectHeighta=40;
            
            ///////////////// Product names From JTable ///////////
        int rows=userTable.getRowCount();
  
           g2d.setFont(new Font("Monospaced",Font.PLAIN,8)); 
            
            g2d.drawString("*******************************",12,y);y+=yShift;
            g2d.drawString("        ZOUR MINI MART        ",12,y);y+=yShift;
            g2d.drawString("*******************************",12,y);y+=headerRectHeight;
                  
             g2d.drawString("        Bill Receipt        ",12,y);y+=yShift;   
             g2d.drawString("-------------------------------------",10,y);y+=yShift;
             g2d.drawString("User:"+user_name+"Date: "+date+"Time: "+time ,10,y);y+=yShift;
             g2d.drawString("-------------------------------------",10,y);y+=yShift;
             g2d.drawString("Qty   Description     Price      Total ",10,y);y+=yShift;
             g2d.drawString("-------------------------------------",10,y);y+=headerRectHeight;
         
             //g2d.setFont(new Font("Monospaced",Font.PLAIN,5));   
      int total_items=0;
           for (int row = 0; row <rows; row++) {
           int item_no=(int)userTable.getValueAt(row, 0);
           int prod_id=(int)userTable.getValueAt(row, 1);
           String prod_name = (String) userTable.getValueAt(row, 2);
     //   Timestamp t_date = (Timestamp) itemTable.getValueAt(row, 1);
        String barcode = (String) userTable.getValueAt(row, 3);
        //String i_name = (String) itemTable.getValueAt(row, 2);
        int quantity =Integer.parseInt(userTable.getValueAt(row, 4).toString());
        int unit_price =Integer.parseInt(userTable.getValueAt(row, 5).toString());
        int total=unit_price*quantity;
        total_items++;                         
            g2d.drawString(quantity+"  "+prod_name+"     "+unit_price+"    "+total,10,y);y+=yShift;
        
      }   
                
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString("Total Items "+total_items,10,y);y+=yShift;
          
            g2d.drawString("                  Total Amount: "+totalAmountTextField.getText()+"               ",10,y);y+=yShift;
            g2d.drawString("                  Cash Amount: "+cashTextField.getText()+"",10,y);y+=yShift;
            g2d.drawString("                  Change: "+ChangeTextField.getText()+"",10,y);y+=yShift;
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString("*************************************",10,y);y+=yShift;
            g2d.drawString("    THANKS TO VISIT OUR MINI MART   ",10,y);y+=yShift;
            g2d.drawString("*************************************",10,y);y+=yShift;
   
    }
    catch(Exception r){
    r.printStackTrace();
    }

              result = PAGE_EXISTS;    
          }    
          return result;    
      }
   }

    private void barcodeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barcodeTextFieldActionPerformed
      String barcode=barcodeTextField.getText();
        getDataByBarcode(barcode);
        barcodeTextField.setText("");
        setTotalAmountInTotalAmountTextFiled();
        
    }//GEN-LAST:event_barcodeTextFieldActionPerformed

    private void cashTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cashTextFieldActionPerformed
        
       change(); 
    }//GEN-LAST:event_cashTextFieldActionPerformed

    private void cashTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cashTextFieldKeyTyped

    }//GEN-LAST:event_cashTextFieldKeyTyped

    private void cashTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cashTextFieldKeyReleased
   
        change();
    }//GEN-LAST:event_cashTextFieldKeyReleased

    private void clearTableButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearTableButtonActionPerformed
DefaultTableModel dm = (DefaultTableModel)userTable.getModel();
while(dm.getRowCount() > 0)
{
    dm.removeRow(0);
totalAmountTextField.setText("");
cashTextField.setText("");
ChangeTextField.setText("");
items=0;

}
    }//GEN-LAST:event_clearTableButtonActionPerformed
/// KeyRealse Method use for set QTY amount in TOTAL AMOUNT CELL
    private void userTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userTableKeyReleased
DefaultTableModel dm = (DefaultTableModel)userTable.getModel();
String sqty=(String)userTable.getValueAt(row,4);
int qty=Integer.parseInt(sqty);
 int t_amount=qty_Total_Amount*qty;
   dm.setValueAt(t_amount,row,6);  
setTotalAmountInTotalAmountTextFiled();
     change();
    }//GEN-LAST:event_userTableKeyReleased

    private void userTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userTableKeyPressed
  
    }//GEN-LAST:event_userTableKeyPressed

    private void userTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userTableMouseClicked
int row = userTable.rowAtPoint(evt.getPoint());
        int col = userTable.columnAtPoint(evt.getPoint());

 //JOptionPane.showMessageDialog(null,"Row =="+ row + "cols"+col);       
         if(col!=4)return;
        if (row >= 0 && col==4) {         
            int price=(int)userTable.getValueAt(row,5);
           //JOptionPane.showMessageDialog(null,"price="+price); 
 setTotalQtyAmount(row ,col,price);
            }

    }//GEN-LAST:event_userTableMouseClicked

    private void SearchButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SearchButtonMouseEntered
SearchButton.setToolTipText("Search Barcode");  
    }//GEN-LAST:event_SearchButtonMouseEntered

    private void clearTableButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearTableButtonMouseEntered
clearTableButton.setToolTipText("Clear Table");    }//GEN-LAST:event_clearTableButtonMouseEntered

    private void printButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printButtonMouseEntered
printButton.setToolTipText("Print");    }//GEN-LAST:event_printButtonMouseEntered

    private void refundItemButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refundItemButtonMouseEntered
refundItemButton.setToolTipText("Refund ITems");
    }//GEN-LAST:event_refundItemButtonMouseEntered
// this give the price and rows in KeyRealesed Method
     private void setTotalQtyAmount(int rows ,int cols,int price){    
         try{       
//           String sqty=(String)userTable.getValueAt(rows, cols);
//           int qty=Integer.parseInt(sqty);
         qty_Total_Amount=price;
         row=rows; 
         }catch(Exception e){
             e.printStackTrace();
             JOptionPane.showMessageDialog(null,"SetTotalQtyAmount..."+e);
         }
    }
     
    private void setTotalAmountInTotalAmountTextFiled(){
        int rows=userTable.getRowCount();
int total_amount=0;
    for (int row = 0; row <rows; row++) 
          total_amount+=(int)userTable.getValueAt(row,6);
           totalAmountTextField.setText(""+total_amount);
        
    } 
    
  private void change(){
      try{  
            String amount=totalAmountTextField.getText();
            String cash=cashTextField.getText();
            if(cash.equals(""))return;
            int t_amount=Integer.parseInt(amount);
            int t_cash=Integer.parseInt(cash);
            int rem=t_cash-t_amount;
            //JOptionPane.showMessageDialog(this,""+rem);
            ChangeTextField.setText(""+rem);
      }catch(Exception e){
          e.printStackTrace();
          JOptionPane.showMessageDialog(null,""+e);
      }
  }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
         UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 13));         

       
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ChangeTextField;
    private javax.swing.JButton SearchButton;
    private javax.swing.JTextField barcodeTextField;
    private javax.swing.JTextField cashTextField;
    private javax.swing.JButton clearTableButton;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton printButton;
    private javax.swing.JButton refundItemButton;
    private javax.swing.JTextField totalAmountTextField;
    private javax.swing.JLabel userImgLabel;
    private javax.swing.JLabel userNameLabel;
    private javax.swing.JTable userTable;
    // End of variables declaration//GEN-END:variables
}
