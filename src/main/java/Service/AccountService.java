package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {

    private AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public Account login(Account user) {
        System.out.println(user);     
        return accountDAO.login(user);
    }

    public Account register(Account user) {
        if(user.getUsername() == "" || user.getPassword().length() < 4){
            return null;
        }else{
            return accountDAO.register(user);
        }
    }

}
