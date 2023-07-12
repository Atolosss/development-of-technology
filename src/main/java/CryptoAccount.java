public class CryptoAccount {

    private int idAccount;

    private Portfolio portfolioAccount;

    public CryptoAccount(int idAccount) {
        this.idAccount = idAccount;

    }


    public int getIdAccount() {
        return idAccount;
    }



    public Portfolio getPortfolioAccount() {
        return portfolioAccount;
    }

    public void setPortfolioAccount(Portfolio portfolioAccount) {
        this.portfolioAccount = portfolioAccount;
    }
}
