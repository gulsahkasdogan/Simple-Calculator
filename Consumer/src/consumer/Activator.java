package consumer;
import javax.swing.*;
import java.awt.*;  

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import calculatorservice.Calculator;
import converterservice.Converter;

public class Activator extends JFrame implements BundleActivator {

	private static BundleContext context;
	static JFrame frame;
	Calculator calculator; //arithmetic operations
	Converter converter; //string-integer conversion
	
	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		
		System.out.println("Consumer starting");
		
		ServiceReference<?> serviceReference = context.getServiceReference(Calculator.class);
        calculator = (Calculator) context.getService(serviceReference);
        
        ServiceReference<?> serviceReference2 = context.getServiceReference(Converter.class);
        converter = (Converter) context.getService(serviceReference2);
        
        /* openPage function creates actionListeners for operations
         * The calculator object representing Calculator service should be instantiated before openPage()
         * function is called  
        */
		openPage();
       
        //System.out.println("5 + 3 = " + calculator.sum(5, 3));
		//System.out.println("Converter: " + converter.stringToInt("5"));
		
		System.out.println("Consumer started");
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;		
		closePage();
		System.out.println("Consumer ended");
	}
	
	public void openPage() {
		if(calculator != null && converter != null) {
			System.out.println("Setting UI...");
			Activator.frame = new JFrame("calculator");
			//frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			
	        frame.setLayout(new FlowLayout(FlowLayout.LEADING));
	        JPanel panel1 = new JPanel(new GridLayout());
	        JPanel panel2 = new JPanel(new GridLayout());
	        JPanel panel3 = new JPanel(new GridLayout());
	        JPanel panel4 = new JPanel(new GridLayout());

	        JTextField number1 = new JTextField(20);
	        JTextField number2 = new JTextField(20);
	        JTextField result = new JTextField(20);
	        result.setEditable(false);
	        result.setText("");

	        JLabel numberOneLabel = new JLabel("Number one: ");
	        JLabel numberTwoLabel = new JLabel("Number two: ");
	        JLabel resultLabel = new JLabel("Result");

	        JButton addButton = new JButton("+"); 
	        JButton subButton = new JButton("-"); 
	        JButton multButton = new JButton("x"); 
	        JButton divButton = new JButton("/"); 

	        addButton.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e){  
	                System.out.println("add");
	                int a = converter.stringToInt(number1.getText());
	                int b = converter.stringToInt(number2.getText());
	            
	                result.setText(converter.intToString(calculator.sum(a, b)));
	            }
	        });

	        subButton.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e){  
	                System.out.println("sub");
	                int a = converter.stringToInt(number1.getText());
	                int b = converter.stringToInt(number2.getText());
	            
	                result.setText(converter.intToString(calculator.subtract(a, b)));
	            }
	        });

	        multButton.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e){  
	                System.out.println("mul");
	                int a = converter.stringToInt(number1.getText());
	                int b = converter.stringToInt(number2.getText());
	            
	                result.setText(converter.intToString(calculator.multiply(a, b)));
	            }
	        });

	        divButton.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e){  
	                System.out.println("div");
	                int a = converter.stringToInt(number1.getText());
	                int b = converter.stringToInt(number2.getText());
	            
	                result.setText(converter.intToString(calculator.divide(a, b)));
	            }
	        });

	        panel1.add(numberOneLabel);
	        panel1.add(number1);

	        panel2.add(numberTwoLabel);
	        panel2.add(number2);

	        panel3.add(resultLabel);
	        panel3.add(result);

	        panel4.add(addButton);
	        panel4.add(subButton);
	        panel4.add(multButton);
	        panel4.add(divButton);
	        
	        frame.add(panel1);
	        frame.add(panel2);
	        frame.add(panel3);
	        frame.add(panel4);
	        
	        frame.setSize(500, 500);
	        frame.setVisible(true);
		}
		else {
			System.out.println("Error: Calculator is null!");
		}
	}
	
	
	public void closePage() {
		System.out.println("Closing page...");
		frame.setVisible(false);
		frame.dispose();
	}
	
}
