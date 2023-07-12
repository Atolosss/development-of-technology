import java.util.Scanner;
import java.util.Set;

public class CryptoExchange {

    private Currency ETH = new Currency("ETH",62.24);

    private Currency BTC = new Currency("BTC",54.12);

    private Set<CryptoAccount> listCryptoAccounts;

    private Set<Currency> listCurrency;


    public Set<Currency> getListCurrency() {
        return listCurrency;
    }

    public void adNewCrypto() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Придумайте название криптовалюты :");
        String acc = sc.nextLine();
        System.out.println("Напишите цену :");
        double price = sc.nextDouble();
        Currency currency = new Currency(acc,price);
        listCurrency.add(currency);
    }


    public void addCryptoAccount(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Придумайте логин криптокошелька :");
        int id = sc.nextInt();
        CryptoAccount cryptoAccount = new CryptoAccount(id);
        listCryptoAccounts.add(cryptoAccount);
    }

    public void delCryptoAccount(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите логин для удаления :");
        int id = sc.nextInt();
        listCryptoAccounts.remove(id);
    }

    public void showPrice(){
        System.out.println("Валюта: "+ETH.getCodeName()+", цена: "+ETH.getPrice() +"\n"+
                "Валюта: "+BTC.getCodeName()+", цена: "+BTC.getPrice());
    }
}
