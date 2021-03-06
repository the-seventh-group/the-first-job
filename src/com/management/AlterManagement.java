package com.management;
 
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
 
import com.database.DBConnection;
 
/**
 * 修改信息
 * @author Administrator
 *
 */
public class AlterManagement extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
 
	JLabel label = new JLabel("修改信息",JLabel.CENTER);
	
	JLabel JLNumber = new JLabel("学号");
	JTextField JTNumber = new JTextField();
	
	JLabel JLName = new JLabel("姓名");
	JTextField JTName = new JTextField();
	
	JLabel JLBirth = new JLabel("生日");
	JTextField JTBirth = new JTextField();
	
	JLabel JLClass = new JLabel("班级");
	JTextField JTClass = new JTextField();
	
	JLabel JLSex = new JLabel("性别");
	//创建按钮组，把两个按钮添加到按钮组中
	ButtonGroup btnGroup = new ButtonGroup();
	//创建两个单选按钮
	JRadioButton radioBtn01 = new JRadioButton("男");
	JRadioButton radioBtn02 = new JRadioButton("女");
		
	
	JLabel JLAcademy = new JLabel("学院");
	JTextField JTAcademy = new JTextField();
	
	JButton ensureBtn = new JButton("确定");
	JButton readdBtn = new JButton("重置");
	JButton cancelBtn = new JButton("取消");
	
	public AlterManagement() {
		this.setTitle("修改信息");
		this.setLayout(null);
		label.setBackground(Color.red);  //label前景色为红色
		label.setFont(new Font("宋体", Font.HANGING_BASELINE, 19));
		label.setBounds(170, 20, 100, 20);
		this.add(label);
		//学号
		JLNumber.setBounds(120, 60, 30, 25);
		JTNumber.setBounds(150, 60, 120, 25);
		this.add(JLNumber);
		this.add(JTNumber);
		
		//姓名
		JLName.setBounds(120, 100, 30, 20);
		JTName.setBounds(150, 100, 120, 25);
		this.add(JLName);
		this.add(JTName);
		
		//性别
		JLSex.setBounds(120,140,30,20);
		radioBtn01.setBounds(150, 140, 60, 20);
		radioBtn02.setBounds(210, 140, 60, 20);
		btnGroup.add(radioBtn01);
		btnGroup.add(radioBtn02);
		this.add(JLSex);
		this.add(radioBtn01);
		this.add(radioBtn02);
 
		//生日
		JLBirth.setBounds(120, 180, 30, 20);
		JTBirth.setBounds(150, 180, 120, 25);
		this.add(JLBirth);
		this.add(JTBirth);
		
		//班级
		JLClass.setBounds(120, 220, 30, 20);
		JTClass.setBounds(150, 220, 120, 25);
		this.add(JLClass);
		this.add(JTClass);
 
		//学院
		JLAcademy.setBounds(120, 260, 30, 20);
		JTAcademy.setBounds(150, 260, 120, 25);
		this.add(JLAcademy);
		this.add(JTAcademy);
		
		ensureBtn.setBounds(100, 320, 60, 25);
		readdBtn.setBounds(170, 320, 60, 25);
		cancelBtn.setBounds(240, 320, 60, 25);
		//添加监听
		ensureBtn.addActionListener(this);
		readdBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		this.add(ensureBtn);
		this.add(readdBtn);
		this.add(cancelBtn);
		
		this.setVisible(true);	
		this.setSize(400, 400); //设置窗口的大小
		this.setLocationRelativeTo(null);//窗体居中显示
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//确定
		if(e.getSource() == ensureBtn) {
			
			Integer snumber = Integer.parseInt(JTNumber.getText());
			String sname = JTName.getText();
			String ssex = "女";
			String sbirth = JTBirth.getText();
			String sclass = JTClass.getText();
			String sacademy = JTAcademy.getText();
				
			
			if(radioBtn01.isSelected()) {
				ssex = "男";
			}
			//检索学生
			String sql = "select * from students where id='"+snumber+"'";
			//打开数据库连接并创建Statement对象
			try {
				Statement stm = DBConnection.getCon().createStatement();
				ResultSet rs = stm.executeQuery(sql);
				if(rs.next()) 
				{
					//更新一条记录
					sql = "update students set name ='"+sname+"',sex='"+ssex+"',birthday='"+sbirth+"',class='"+sclass+"',academy='"+sacademy+"'where id="+snumber;
					int i = stm.executeUpdate(sql);
					if(i>0) {
						JOptionPane.showMessageDialog(null, "修改成功！", "提示信息", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null, "修改失败！", "提示信息", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else 
				{
					JOptionPane.showMessageDialog(null, "该账号不存在！", "提示信息", JOptionPane.WARNING_MESSAGE);
					
				}
				stm.close();
			} 
			catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
		
		//重置
		if(e.getSource() == readdBtn) {
			JTNumber.setText(null);
			JTName .setText(null);
			JTClass.setText(null);
			JTAcademy.setText(null);
			JTBirth.setText(null);
		}
		
		//取消
		if(e.getSource() == cancelBtn) {
			setVisible(false);
		}
		
	}
 
}
