package view;
import model.Auth;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

import prototype.controller;


public class gui {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Button btnLogin;
	
	static controller cont = new controller();
	private Label lblWelcomeToMy;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			gui window = new gui();
			window.open();
			//cont = new controller();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(498, 300);
		shell.setText("SWT Application");
		
		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setBounds(0, 65, 482, 197);
		
		TabItem tbtmHome_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmHome_1.setText("Home");
		
		lblWelcomeToMy = new Label(tabFolder, SWT.NONE);
		tbtmHome_1.setControl(lblWelcomeToMy);
		lblWelcomeToMy.setText("Welcome to my CSA App! -- Powered by WindowBuilder and brought to you by Bertrand");
		
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("Jobs");
		
		TabItem tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText("Profile");
		
		TabItem tbtmNewItem_2 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_2.setText("User");
		
		TabItem tbtmNewItem_3 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_3.setText("Broadcasts");
		
		Button btnEnglish = new Button(shell, SWT.NONE);
		btnEnglish.setBounds(0, 0, 75, 25);
		btnEnglish.setText("English");
		
		Button btnCymraeg = new Button(shell, SWT.NONE);
		btnCymraeg.setBounds(0, 31, 75, 25);
		btnCymraeg.setText("Cymraeg");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(177, 0, 76, 21);
		
		Label lblUsername = new Label(shell, SWT.NONE);
		lblUsername.setBounds(116, 5, 55, 15);
		lblUsername.setText("Username");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(326, 0, 76, 21);
		
		Label lblPassword = new Label(shell, SWT.NONE);
		lblPassword.setBounds(265, 5, 55, 15);
		lblPassword.setText("Password");
		
		btnLogin = new Button(shell, SWT.NONE);

		btnLogin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cont.readAuth(text.getSelectionText(), text_1.getSelectionText(), null);
			}
		});
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				cont.readAuth(text.getSelectionText(), text_1.getSelectionText(), null);
			}
		});
		btnLogin.setBounds(407, 0, 75, 25);
		btnLogin.setText("Login");
		
		Label label = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(88, -5, 2, 64);
		
		Label label_1 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(418, 31, 64, 2);
		
		Label label_2 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_2.setBounds(353, 31, 64, 2);
		
		Label label_3 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_3.setBounds(88, 31, 273, 2);
		
		Button btnForgotPassw = new Button(shell, SWT.NONE);
		
		btnForgotPassw.setBounds(407, 34, 75, 25);
		btnForgotPassw.setText("Forgot");
		
		Button btnRegister = new Button(shell, SWT.NONE);
		btnRegister.setBounds(326, 34, 75, 25);
		btnRegister.setText("Register");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//cont.create(Auth.get_username(), Auth.get_password(), "ffgfdfffegr@sdf.com", "first", "1985", "false", "1234567", "surN");
				cont.createWithAuthentication("http://localhost:3000/users.json?user[email]=ffgfdfffegr@sdf.com&user[firstname]=firstname&user[grad_year]=1985&user[jobs]=jobs&user[phone]=1234567&user[surname]=surname");
				//String string = "dddddd";
				
				//getLblWelcomeToMy().setText(string);
				
			}
		});
		btnNewButton.setBounds(116, 34, 75, 25);
		btnNewButton.setText("New Button");

	}
	public Button getBtnLogin() {
		return btnLogin;
	}
	public Text getText() {
		return text;
	}
	public Text getText_1() {
		return text_1;
	}
	public Label getLblWelcomeToMy() {
		return lblWelcomeToMy;
	}
}
