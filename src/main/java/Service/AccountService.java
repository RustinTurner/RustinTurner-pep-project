package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {

    private AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public Account login(Account user) {
        return accountDAO.login(user);
    }

    public Account register(Account user) {
        return accountDAO.register(user);
    }

}
