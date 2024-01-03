import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class FitnessTrackerCalculator extends JFrame implements ActionListener {
    private JLabel  stepsLabel, genderLabel,weightLabel,heightLabel,modeofwalkLabel,activityLabel,resultLabel,resultLabel1,resultLabel2;
    private JTextField  stepsField, heightField,weightField;
    private JRadioButton maleButton, femaleButton;
    private JComboBox<String> heightUnitComboBox;
    private JComboBox<String> activityDropdown,paceofwalk;
    private JButton calculateButton,clearButton;
    private String[] activities = {"Sitting quietly", "Watching TV", "Light walking (2.5 mph)", "Brisk walking (3.5 mph)",
        "Bicycling (10-12 mph)", "Swimming (leisurely)", "Jogging/Running", "Basketball", "Soccer/Football"};
    private double[] metValues = {1.3, 1.3, 2.5, 3.5, 6.0, 5.0, 7.0, 6.0, 7.0};
    private String[] movement={" very slow walk","slow walk","briskwalk","jog","run","Fast run","Very fast Run"};
    private double[] Menstridelength={2.2,2.3,3.0,3.2,4.0,5.5,6.0};
    private double[] Womstridelength={2.0,2.2,2.7,3.0,3.5,4.8,5.2};

    public FitnessTrackerCalculator() {
        setTitle("Health And Fitness Tracker");
        setSize(420, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLayout(null);
        
        ImageIcon icon = new ImageIcon("C:\\Users\\hello\\OneDrive\\Desktop\\fit.jpg");
        setIconImage(icon.getImage());
        genderLabel=new JLabel("Enter Gender :");
        genderLabel.setBounds(20,20,150,20);
        add(genderLabel);

        maleButton=new JRadioButton("Male");
        maleButton.setBounds(180,20,150,20);
        add(maleButton);

        femaleButton=new JRadioButton("Female");
        femaleButton.setBounds(180,40,150,40);
        add(femaleButton);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);

       
        heightLabel = new JLabel("Enter Height :");
        heightLabel.setBounds(20, 100, 150, 20);
        add(heightLabel);

        heightField = new JTextField();
        heightField.setBounds(180, 100, 100, 23);
        add(heightField);

        String[] heightUnits = {"Feet.inches", "centimeters"};
        heightUnitComboBox = new JComboBox<>(heightUnits);
        heightUnitComboBox.setBounds(290, 100, 100, 20);
        add(heightUnitComboBox);

        weightLabel=new JLabel("Enter Weight(kg):");
        weightLabel.setBounds(20, 130, 150, 20);
        add(weightLabel);

        weightField=new JTextField();
        weightField.setBounds(180, 130, 100, 23);
        add(weightField);

        modeofwalkLabel=new JLabel("Pace of movement:");
        modeofwalkLabel.setBounds(20, 160, 220, 20);
        add(modeofwalkLabel);

        paceofwalk=new JComboBox<>(movement);
        paceofwalk.setBounds(180, 160, 150, 25);
        add(paceofwalk);


        activityLabel=new JLabel("Activities :");
        activityLabel.setBounds(20, 200, 220, 20);
        add(activityLabel);

         activityDropdown = new JComboBox<>(activities);
        activityDropdown.setBounds(180, 200, 150, 25);
        add(activityDropdown);

        stepsLabel=new JLabel("Steps :");
        stepsLabel.setBounds(20, 240, 150, 20);
        add(stepsLabel);

        stepsField=new JTextField();
        stepsField.setBounds(180,240,100,23);
        add(stepsField);

        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(120, 270, 120, 30);
        add(calculateButton);
        calculateButton.addActionListener(this);

        resultLabel = new JLabel("");
        resultLabel.setBounds(100, 300, 200, 20);
        add(resultLabel);

        resultLabel1=new JLabel("");
        resultLabel1.setBounds(20,320,300,20);
        add(resultLabel1);

        resultLabel2=new JLabel("");
        resultLabel2.setBounds(40,340,410,20);
        add(resultLabel2);

        clearButton=new JButton("Clear");
        clearButton.setBounds(140, 380, 80, 30);
        add(clearButton);
        clearButton.addActionListener(this);

        getContentPane().setBackground(Color.lightGray); // Change the background color

        
        heightField.setBackground(Color.white);
        weightField.setBackground(Color.white);
        stepsField.setBackground(Color.white);
        calculateButton.setBackground(Color.green); 
        clearButton.setBackground(Color.orange); 
        resultLabel.setForeground(Color.blue); 
        resultLabel1.setForeground(Color.blue);
        resultLabel2.setForeground(Color.blue);


        setLayout(new BorderLayout()); // Example: BorderLayout

        // Use a different look-and-feel style
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            try {
                double heightValue = Double.parseDouble(heightField.getText());
                double stepsvalue=Double.parseDouble(stepsField.getText());
               int weightvalue=Integer.parseInt(weightField.getText());
               int selectedIndex = activityDropdown.getSelectedIndex();
                double metValue = metValues[selectedIndex];
               double caloriesBurned = metValue * weightvalue * (stepsvalue / 2000.0); // Assuming 2000 steps ≈ 1 mile=1.609km
                String selectedHeightUnit = (String) heightUnitComboBox.getSelectedItem();
                double height;
                if (selectedHeightUnit.equals("Feet.inches")) {
                    height = heightValue*30.48;
                } else {
                    height = heightValue ;
                }
                double hi=height/100;
                double BMI=weightvalue/(hi*hi);

                int index=paceofwalk.getSelectedIndex();
                double DistanceCovered,miles,kilometer;
                if (maleButton.isSelected()) {
                   double sl=Menstridelength[index];
                    DistanceCovered=sl*stepsvalue;
                    miles=DistanceCovered/5280;
                    kilometer=miles*1.609;
                } else {
                  double sl=Womstridelength[index];
                    DistanceCovered=sl*stepsvalue;
                    miles=DistanceCovered/5280;
                    kilometer=miles*1.609;
                }
                String category;
                if (BMI < 18.5) {
                    category = "Underweight";
                  } else if (BMI>18.5 && BMI <= 25) {
                    category = "Normal weight";
                  } else if (BMI>25 && BMI <= 30) {
                    category = "Overweight";
                  } else {
                    category = "Obese";
                  }
                resultLabel.setText("Estimated Calories Burn: " + caloriesBurned+"cal");
                resultLabel1.setText("Your BMI value is :"+BMI+" , Category: "+ category);
                resultLabel2.setText("The distance you covered is: "+kilometer+" Km ");
            


            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter valid numbers.");
            }          
        }
        else if(e.getSource()==clearButton){
          heightField.setText("");
          weightField.setText("");
          stepsField.setText("");
         resultLabel.setText("");
          resultLabel1.setText("");
          resultLabel2.setText("");
          ButtonGroup genderGroup = new ButtonGroup();
          genderGroup.add(maleButton);
          genderGroup.add(femaleButton);
          genderGroup.clearSelection();

        }
    }

    public static void main(String[] args) {
       new FitnessTrackerCalculator();}
}