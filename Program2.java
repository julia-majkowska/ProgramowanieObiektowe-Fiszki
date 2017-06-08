public class Program2{
	Program2(){
		User user = null;
		UserList2 ulist = new UserList2();
		
		Okno_m m = new Okno_m();
		
		while ( user == null ){
			UserLogin typedLogin = m.menu(); //new UserLogin("ala","h1");//
			
			while ( typedLogin.login == "" ){
				UserLogin newLogin = m.newUserMenu();//new UserLogin("aga","h2");//
				try{
					ulist.addUser(newLogin);
				}catch(SizeException e){
					return;
				}catch(StopException e){
					return;
				}
				typedLogin = m.menu();
			}
			try{
				user = ulist.chooseUser( typedLogin );
			}catch(StopException e) {
				return;
			}
		}
		System.out.println("Hello " + user.login + "!");
	}
	public static void main(String[] args){	
		Program2 p = new Program2();
	}
}