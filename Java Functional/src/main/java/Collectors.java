public class Collectors {
    /*
    producing collections;
    partitioning and grouping;
    producing values;
    downstream collectors.
     */

    public static void main(String[] args) {
        // Производство коллекций
        // У любого стрима есть метод collect()

//        Предположим, у нас есть список аккаунтов. У каждой учетной записи есть баланс
//        (Long), уникальный номер (String) и некоторые другие поля.

//        List<String> numberList = accounts.stream()
//                .map(Account::getNumber)
//                .collect(Collectors.toList());

//        Set<String> numberSet = accounts.stream()
//                .map(Account::getNumber)
//                .collect(Collectors.toSet());

//        Map<String, Long> numberToBalanceMap = accounts.stream()
//                .collect(Collectors.toMap(Account::getNumber, Account::getBalance));

//        stream().collect(Collectors.toCollection(HashSet::new));

        // группировка коллекций
        // partitioning - разбиение коллекции по предикату

//        Map<Boolean, List<Account>> partByBalance = accounts.stream()
//                .collect(Collectors.partitioningBy(a -> a.getBalance() >= 10000));

        // Примерный результат
//        {
//            false=[Account{balance=3333, number='530012'}, Account{balance=0, number='681891'}],
//            true=[Account{balance=15000, number='771843'}]
//        }

        // Завели enum с 3-я состояниеми
//        Map<Account.State, List<Account>> groupingByState = accounts.stream()
//                .collect(Collectors.groupingBy(Account::getState));

        // результат
//        {
//            REMOVED=[Account{balance=0, number='681891', state='REMOVED'}],
//            ACTIVE=[Account{balance=3333, number='530012', state='ACTIVE'}],
//            BLOCKED=[Account{balance=15000, number='771843', state='BLOCKED'}]
//        }


    }
}
