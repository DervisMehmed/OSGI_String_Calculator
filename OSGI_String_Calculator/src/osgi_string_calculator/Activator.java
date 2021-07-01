package osgi_string_calculator;

import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;


import osgi_string_calculator_services.ConvertorService;

/*
 *	This class initializes the UI components and their listeners,
 *	uses the service package for the conversions.
 *	Default language is Turkish. However, if it detects the language is English,
 *	it loads the English user interface. According to languages, service package
 *	loads the arrays.
 *	
 *	The conversion limit is INT_MAX.
 */

public class Activator implements BundleActivator {

	private static BundleContext context;
	ServiceReference<?> serviceReference;
	private ConvertorService service;
	
	JFrame	jFrame;
	JButton jButton1;
    JButton jButton2;
    JButton jButton3;
    JButton jButton4;
    JLabel jLabel1;
    JLabel jLabel2;
    JLabel jLabel3;
    JLabel jLabel4;
    JPanel jPanel1;
    JPanel jPanel2;
    JPanel jPanel3;
    JTextField jTextField1;
    JTextField jTextField2;
    JTextField jTextField3;
	
	String jButton1TR = "Topla";
    String jButton2TR = "Çıkar";
    String jButton3TR = "Çarp";
    String jButton4TR = "Böl";
    String jLabel1TR = "İlk sayı";
    String jLabel2TR = "İkinci sayı";
    String jLabel3TR = "Sonuç";
    
    String jButton1EN = "Sum";
    String jButton2EN = "Minus";
    String jButton3EN = "Multiply ";
    String jButton4EN = "Divide";
    String jLabel1EN = "First Number";
    String jLabel2EN = "Second Number";
    String jLabel3EN = "Result";
    
	static Locale locale;
    static String lang = "tr";
    
    int resultNumInt = 0;
    
    boolean flagEN = false;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		System.out.println("OSGI String Convertor Starting...");
		
		jFrame = new JFrame();
		locale  = Locale.getDefault();
		lang = locale.getLanguage();
		
		serviceReference = context.getServiceReference(ConvertorService.class);
		service = (ConvertorService) context.getService(serviceReference);
		
		if(lang.equals("en")) 
		{
			flagEN = true;
			lang = "en";
		}
		service.loadArrays(lang);
		
		initComponents(flagEN);
		
		jFrame.setVisible(true);
	    System.out.println("OSGI String Convertor Started...");
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		
		System.out.println("OSGI String Convertor Stopped...");
	}
	
	//	UI Components initialize here
	//	Depends on the language flag, loads accordingly
	private void initComponents(boolean lang)
    {
        String label1;
        String label2;
        String label3;
        String bLabel1;
        String bLabel2;
        String bLabel3;
        String bLabel4;
        
        if(!flagEN)
        {
            label1 = jLabel1TR;
            label2 = jLabel2TR;
            label3 = jLabel3TR;
            bLabel1= jButton1TR;
            bLabel2= jButton2TR;
            bLabel3= jButton3TR;
            bLabel4= jButton4TR;
        }
        else{
            label1 = jLabel1EN;
            label2 = jLabel2EN;
            label3 = jLabel3EN;
            bLabel1= jButton1EN;
            bLabel2= jButton2EN;
            bLabel3= jButton3EN;
            bLabel4= jButton4EN;
        }
        
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jFrame.setMaximumSize(new java.awt.Dimension(800, 600));
        jFrame.setPreferredSize(new java.awt.Dimension(500, 250));

        jPanel1.setLayout(new java.awt.GridLayout(3, 2, 0, 20));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(label1);
        jPanel1.add(jLabel1);

        jTextField1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPanel1.add(jTextField1);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(label2);
        jPanel1.add(jLabel2);

        jTextField2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPanel1.add(jTextField2);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setLabelFor(jTextField3);
        jLabel3.setText(label3);
        jPanel1.add(jLabel3);

        jTextField3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPanel1.add(jTextField3);

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPanel3.add(jLabel4);

        jButton1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton1.setText(bLabel1);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jButton2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton2.setText(bLabel2);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);

        jButton4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton4.setText(bLabel4);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4);

        jButton3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton3.setText(bLabel3);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(jFrame.getContentPane());
        jFrame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 27, Short.MAX_VALUE))
        );

        jFrame.pack();
    }
	
	//  Listener of Sum Button
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) 
    {
        int[] num1 = service.stringToInt(jTextField1.getText().toLowerCase(), jTextField2.getText().toLowerCase(), flagEN);
        
        resultNumInt = num1[0] + num1[1];
        
        jTextField3.setText(service.intToString(resultNumInt, flagEN));
        
        jLabel4.setText(num1[0] + " + " + num1[1] + " = " + resultNumInt);
    }
    //  Listener of Minus Button
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) 
    {
        int[] num1 = service.stringToInt(jTextField1.getText().toLowerCase(), jTextField2.getText().toLowerCase(), flagEN);
        
        resultNumInt = num1[0] - num1[1];
        jTextField3.setText(service.intToString(resultNumInt, flagEN));
                
        jLabel4.setText(num1[0] + " - " + num1[1] + " = " + resultNumInt);
    }
    //  Listener of Divide Button
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) 
    {
        int[] num1 = service.stringToInt(jTextField1.getText().toLowerCase(), jTextField2.getText().toLowerCase(), flagEN);
        
        if( num1[1] != 0){
            resultNumInt = num1[0] / num1[1];
            jTextField3.setText(service.intToString(resultNumInt, flagEN));

            jLabel4.setText(num1[0] + " / " + num1[1] + " = " + resultNumInt);
        }
        else
        {
            jLabel4.setText(num1[0] + " cannot be divided by zero.");
        }
    }
    //  Listener of Multiply Button
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) 
    {
        int[] num1 = service.stringToInt(jTextField1.getText().toLowerCase(), jTextField2.getText().toLowerCase(), flagEN);
        
        resultNumInt = num1[0] * num1[1];
        jTextField3.setText(service.intToString(resultNumInt, flagEN));
                
        jLabel4.setText(num1[0] + " x " + num1[1] + " = " + resultNumInt);
    }

}
