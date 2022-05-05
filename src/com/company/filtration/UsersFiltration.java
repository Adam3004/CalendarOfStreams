package com.company.filtration;

import com.company.exceptionMethods.NullInputExceptionMethod;
import com.company.exceptions.NullInputException;
import com.company.role.Role;
import com.company.user.User;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class UsersFiltration {
    public static void method(List<User> users, Scanner sc) {
        String order;
        int numberOfOrder;
        List<User> resultList = users;

        while (true) {
            System.out.println("Filter by: ");
            System.out.println("1. Age");
            System.out.println("2. Role");
            System.out.println("Choose number: ");
            order = sc.nextLine();
            try {
                NullInputExceptionMethod.method(order);
                numberOfOrder = Integer.parseInt(order);
            } catch (NullInputException e) {
                e.printStackTrace();

                return;
            } catch (NumberFormatException e) {
                System.out.println("It is not a number");
                e.printStackTrace();

                return;
            }
            switch (numberOfOrder) {
                case 1:
                    resultList = ageFilter(sc, resultList);
                    sc.nextLine();

                    break;
                case 2:
                    resultList = roleFilter(sc, resultList);

                    break;

                default:
                    System.out.println("wrong number");
            }
            Stream<User> outPut = resultList.stream();
            outPut
                    .map(user -> "Login: " + user.getLogin() + " Password: " + user.getPassword() + " Age: " +
                            user.getAge() + " Role: " + user.getRole())
                    .forEach(System.out::println);
            System.out.println("Do you want to add more filters?: (Y/N)");
            order = sc.nextLine();
            if (!order.equals("Y")) {

                break;
            }
        }
    }

    public static List<User> ageFilter(Scanner sc, List<User> resultList) {
        String order;
        int numberOfOrder;
        int age;
        int secondAge;
        System.out.println("Choose option: ");
        System.out.println("1. Older than");
        System.out.println("2. Younger than");
        System.out.println("3. Between");
        System.out.println("4. At the age of");
        order = sc.nextLine();
        try {
            NullInputExceptionMethod.method(order);
            numberOfOrder = Integer.parseInt(order);
        } catch (NullInputException e) {
            e.printStackTrace();

            return null;
        } catch (NumberFormatException e) {
            System.out.println("It is not a number");
            e.printStackTrace();

            return null;
        }
        Stream<User> results = resultList.stream();
        switch (numberOfOrder) {
            case 1:
                System.out.println("Older than: (enter age)");
                age = sc.nextInt();
                resultList = results
                        .filter(u -> u.getAge() > age)
                        .collect(Collectors.toList());

                break;
            case 2:
                System.out.println("Younger than: (enter age)");
                age = sc.nextInt();
                resultList = results
                        .filter(u -> u.getAge() < age)
                        .collect(Collectors.toList());

                break;
            case 3:
                System.out.println("Older than: (enter age)");
                age = sc.nextInt();
                System.out.println("Younger than: (enter age)");
                secondAge = sc.nextInt();
                resultList = results
                        .filter(u -> u.getAge() > age)
                        .filter(u -> u.getAge() < secondAge)
                        .collect(Collectors.toList());

                break;
            case 4:
                System.out.println("At the age of: (enter age)");
                age = sc.nextInt();
                resultList = results
                        .filter(u -> u.getAge() == age)
                        .collect(Collectors.toList());

                break;
            default:
                System.out.println("wrong number");
        }

        return resultList;
    }

    public static List<User> roleFilter(Scanner sc, List<User> resultList) {
        String order;
        int numberOfOrder;

        System.out.println("Choose option: ");
        System.out.println("1. Show moderators");
        System.out.println("2. Show viewers");
        System.out.println("3. Show streamers");
        System.out.println("4. Show moderators and viewers");
        System.out.println("5. Show viewers and streamers");
        System.out.println("6. Show moderators and streamers");
        order = sc.nextLine();
        try {
            NullInputExceptionMethod.method(order);
            numberOfOrder = Integer.parseInt(order);
        } catch (NullInputException e) {
            e.printStackTrace();

            return null;
        } catch (NumberFormatException e) {
            System.out.println("It is not a number");
            e.printStackTrace();

            return null;
        }
        Stream<User> results = resultList.stream();
        switch (numberOfOrder) {
            case 1:
                resultList = results
                        .filter(u -> u.getRole() == Role.MOD)
                        .collect(Collectors.toList());

                break;
            case 2:
                resultList = results
                        .filter(u -> u.getRole() == Role.VIEWER)
                        .collect(Collectors.toList());

                break;
            case 3:
                resultList = results
                        .filter(u -> u.getRole() == Role.STREAMER)
                        .collect(Collectors.toList());

                break;
            case 4:
                resultList = results
                        .filter(u -> u.getRole() == Role.MOD || u.getRole() == Role.VIEWER)
                        .collect(Collectors.toList());

                break;
            case 5:
                resultList = results
                        .filter(u -> u.getRole() == Role.STREAMER || u.getRole() == Role.VIEWER)
                        .collect(Collectors.toList());

                break;
            case 6:
                resultList = results
                        .filter(u -> u.getRole() == Role.MOD || u.getRole() == Role.STREAMER)
                        .collect(Collectors.toList());

                break;
            default:
                System.out.println("wrong number");
        }

        return resultList;
    }

//    wiem ze sposob napisania printera jest slaby bo jest malo czytelny i sa to zapetlone if i else'y ale na obecny moment
//    nie potrafie napisac tego lepiej, dlatego poki co nie biore sie za filtracje eventow, jezeli znajde cos bardziej
//    efektywnego to moze i to zmienie, jednak sporo by tu bylo do przekopania sie, w kazdym razie wiem ze zrobilem zle
//    ale lepiej jeszcze nie potrafie
//    EDIT: filtorwanie eventow jest zrobione lepiej, ale tego juz nie bede zmienial, zostawie na pamiatke, to projekt tylko dla mnie
//    wiec niech zostanie jak jest
//    EDIT 2: nie chciałem zostawic go tak zle napisanego wiec napisalem lepiej a jego zostawie tylko po to żeby pokazać
//    progres jaki zrobiłem w trakcie tego projektu, może obecna filtracja też nie jest idealna, ale na pewno jest dużo lepsza

//    public static void printer(Map<String, String> commands, List<User> users) {
//        int size = commands.size();
//        int age;
//        int secondAge;
//        int age2;
//        int secondAge2;
//        switch (size) {
//            case 1:
//                if (commands.containsKey("MOD") && commands.containsValue("VIEWER")) {
//                    users.stream()
//                            .filter(c -> c.getRole() == Role.MOD || c.getRole() == Role.VIEWER)
//                            .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                            .forEach(System.out::println);
//                } else if (commands.containsKey("VIEWER") && commands.containsValue("STREAMER")) {
//                    users.stream()
//                            .filter(c -> c.getRole() == Role.STREAMER || c.getRole() == Role.VIEWER)
//                            .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                            .forEach(System.out::println);
//                } else if (commands.containsKey("STREAMER") && commands.containsValue("MOD")) {
//                    users.stream()
//                            .filter(c -> c.getRole() == Role.STREAMER || c.getRole() == Role.MOD)
//                            .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                            .forEach(System.out::println);
//                } else if (commands.containsKey("MOD")) {
//                    users.stream()
//                            .filter(c -> c.getRole() == Role.MOD)
//                            .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                            .forEach(System.out::println);
//                } else if (commands.containsKey("VIEWER")) {
//                    users.stream()
//                            .filter(c -> c.getRole() == Role.VIEWER)
//                            .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                            .forEach(System.out::println);
//                } else if (commands.containsKey("STREAMER")) {
//                    users.stream()
//                            .filter(c -> c.getRole() == Role.STREAMER)
//                            .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                            .forEach(System.out::println);
//                } else if (commands.containsKey("Older")) {
//                    age = Integer.parseInt(commands.get("Older"));
//                    users.stream()
//                            .filter(c -> c.getAge() > age)
//                            .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                            .forEach(System.out::println);
//                } else if (commands.containsKey("Younger")) {
//                    age = Integer.parseInt(commands.get("Younger"));
//                    users.stream()
//                            .filter(c -> c.getAge() < age)
//                            .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                            .forEach(System.out::println);
//                } else if (commands.containsKey("Between")) {
//                    String[] ages = commands.get("Between").split(" ");
//                    age = Integer.parseInt(ages[0]);
//                    secondAge = Integer.parseInt(ages[1]);
//                    users.stream()
//                            .filter(c -> c.getAge() > age && c.getAge() < secondAge)
//                            .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                            .forEach(System.out::println);
//                } else if (commands.containsKey("At the age")) {
//                    age = Integer.parseInt(commands.get("At the age"));
//                    users.stream()
//                            .filter(c -> c.getAge() == age)
//                            .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                            .forEach(System.out::println);
//                }
//                break;
//            case 2:
//                if (commands.containsKey("MOD") && commands.containsValue("VIEWER")) {
//                    if (commands.containsKey("Older")) {
//                        age2 = Integer.parseInt(commands.get("Older"));
//                        users.stream()
//                                .filter(c -> c.getAge() > age2)
//                                .filter(c -> c.getRole() == Role.MOD || c.getRole() == Role.VIEWER)
//                                .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                                .forEach(System.out::println);
//                    } else if (commands.containsKey("Younger")) {
//                        age2 = Integer.parseInt(commands.get("Younger"));
//                        users.stream()
//                                .filter(c -> c.getAge() < age2)
//                                .filter(c -> c.getRole() == Role.MOD || c.getRole() == Role.VIEWER)
//                                .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                                .forEach(System.out::println);
//                    } else if (commands.containsKey("Between")) {
//                        String[] ages = commands.get("Between").split(" ");
//                        age2 = Integer.parseInt(ages[0]);
//                        secondAge2 = Integer.parseInt(ages[1]);
//                        users.stream()
//                                .filter(c -> c.getAge() > age2 && c.getAge() < secondAge2)
//                                .filter(c -> c.getRole() == Role.MOD || c.getRole() == Role.VIEWER)
//                                .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                                .forEach(System.out::println);
//                    } else if (commands.containsKey("At the age")) {
//                        age2 = Integer.parseInt(commands.get("At the age"));
//                        users.stream()
//                                .filter(c -> c.getAge() == age2)
//                                .filter(c -> c.getRole() == Role.MOD || c.getRole() == Role.VIEWER)
//                                .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                                .forEach(System.out::println);
//                    }
//                } else if (commands.containsKey("VIEWER") && commands.containsValue("STREAMER")) {
//                    if (commands.containsKey("Older")) {
//                        age2 = Integer.parseInt(commands.get("Older"));
//                        users.stream()
//                                .filter(c -> c.getAge() > age2)
//                                .filter(c -> c.getRole() == Role.STREAMER || c.getRole() == Role.VIEWER)
//                                .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                                .forEach(System.out::println);
//                    } else if (commands.containsKey("Younger")) {
//                        age2 = Integer.parseInt(commands.get("Younger"));
//                        users.stream()
//                                .filter(c -> c.getAge() < age2)
//                                .filter(c -> c.getRole() == Role.STREAMER || c.getRole() == Role.VIEWER)
//                                .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                                .forEach(System.out::println);
//                    } else if (commands.containsKey("Between")) {
//                        String[] ages = commands.get("Between").split(" ");
//                        age2 = Integer.parseInt(ages[0]);
//                        secondAge2 = Integer.parseInt(ages[1]);
//                        users.stream()
//                                .filter(c -> c.getAge() > age2 && c.getAge() < secondAge2)
//                                .filter(c -> c.getRole() == Role.STREAMER || c.getRole() == Role.VIEWER)
//                                .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                                .forEach(System.out::println);
//                    } else if (commands.containsKey("At the age")) {
//                        age2 = Integer.parseInt(commands.get("At the age"));
//                        users.stream()
//                                .filter(c -> c.getAge() == age2)
//                                .filter(c -> c.getRole() == Role.STREAMER || c.getRole() == Role.VIEWER)
//                                .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                                .forEach(System.out::println);
//                    }
//                } else if (commands.containsKey("STREAMER") && commands.containsValue("MOD")) {
//                    if (commands.containsKey("Older")) {
//                        age2 = Integer.parseInt(commands.get("Older"));
//                        users.stream()
//                                .filter(c -> c.getAge() > age2)
//                                .filter(c -> c.getRole() == Role.STREAMER || c.getRole() == Role.MOD)
//                                .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                                .forEach(System.out::println);
//                    } else if (commands.containsKey("Younger")) {
//                        age2 = Integer.parseInt(commands.get("Younger"));
//                        users.stream()
//                                .filter(c -> c.getAge() < age2)
//                                .filter(c -> c.getRole() == Role.STREAMER || c.getRole() == Role.MOD)
//                                .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                                .forEach(System.out::println);
//                    } else if (commands.containsKey("Between")) {
//                        String[] ages = commands.get("Between").split(" ");
//                        age2 = Integer.parseInt(ages[0]);
//                        secondAge2 = Integer.parseInt(ages[1]);
//                        users.stream()
//                                .filter(c -> c.getAge() > age2 && c.getAge() < secondAge2)
//                                .filter(c -> c.getRole() == Role.STREAMER || c.getRole() == Role.MOD)
//                                .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                                .forEach(System.out::println);
//                    } else if (commands.containsKey("At the age")) {
//                        age2 = Integer.parseInt(commands.get("At the age"));
//                        users.stream()
//                                .filter(c -> c.getAge() == age2)
//                                .filter(c -> c.getRole() == Role.STREAMER || c.getRole() == Role.MOD)
//                                .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                                .forEach(System.out::println);
//                    }
//                } else if (commands.containsKey("MOD")) {
//                    if (commands.containsKey("Older")) {
//                        age2 = Integer.parseInt(commands.get("Older"));
//                        users.stream()
//                                .filter(c -> c.getAge() > age2)
//                                .filter(c -> c.getRole() == Role.MOD)
//                                .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                                .forEach(System.out::println);
//                    } else if (commands.containsKey("Younger")) {
//                        age2 = Integer.parseInt(commands.get("Younger"));
//                        users.stream()
//                                .filter(c -> c.getAge() < age2)
//                                .filter(c -> c.getRole() == Role.MOD)
//                                .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                                .forEach(System.out::println);
//                    } else if (commands.containsKey("Between")) {
//                        String[] ages = commands.get("Between").split(" ");
//                        age2 = Integer.parseInt(ages[0]);
//                        secondAge2 = Integer.parseInt(ages[1]);
//                        users.stream()
//                                .filter(c -> c.getAge() > age2 && c.getAge() < secondAge2)
//                                .filter(c -> c.getRole() == Role.MOD)
//                                .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                                .forEach(System.out::println);
//                    } else if (commands.containsKey("At the age")) {
//                        age2 = Integer.parseInt(commands.get("At the age"));
//                        users.stream()
//                                .filter(c -> c.getAge() == age2)
//                                .filter(c -> c.getRole() == Role.MOD)
//                                .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                                .forEach(System.out::println);
//                    }
//
//                } else if (commands.containsKey("VIEWER")) {
//                    if (commands.containsKey("Older")) {
//                        age2 = Integer.parseInt(commands.get("Older"));
//                        users.stream()
//                                .filter(c -> c.getAge() > age2)
//                                .filter(c -> c.getRole() == Role.VIEWER)
//                                .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                                .forEach(System.out::println);
//                    } else if (commands.containsKey("Younger")) {
//                        age2 = Integer.parseInt(commands.get("Younger"));
//                        users.stream()
//                                .filter(c -> c.getAge() < age2)
//                                .filter(c -> c.getRole() == Role.VIEWER)
//                                .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                                .forEach(System.out::println);
//                    } else if (commands.containsKey("Between")) {
//                        String[] ages = commands.get("Between").split(" ");
//                        age2 = Integer.parseInt(ages[0]);
//                        secondAge2 = Integer.parseInt(ages[1]);
//                        users.stream()
//                                .filter(c -> c.getAge() > age2 && c.getAge() < secondAge2)
//                                .filter(c -> c.getRole() == Role.VIEWER)
//                                .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                                .forEach(System.out::println);
//                    } else if (commands.containsKey("At the age")) {
//                        age2 = Integer.parseInt(commands.get("At the age"));
//                        users.stream()
//                                .filter(c -> c.getAge() == age2)
//                                .filter(c -> c.getRole() == Role.VIEWER)
//                                .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                                .forEach(System.out::println);
//                    }
//                } else if (commands.containsKey("STREAMER")) {
//                    if (commands.containsKey("Older")) {
//                        age2 = Integer.parseInt(commands.get("Older"));
//                        users.stream()
//                                .filter(c -> c.getAge() > age2)
//                                .filter(c -> c.getRole() == Role.STREAMER)
//                                .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                                .forEach(System.out::println);
//                    } else if (commands.containsKey("Younger")) {
//                        age2 = Integer.parseInt(commands.get("Younger"));
//                        users.stream()
//                                .filter(c -> c.getAge() < age2)
//                                .filter(c -> c.getRole() == Role.STREAMER)
//                                .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                                .forEach(System.out::println);
//                    } else if (commands.containsKey("Between")) {
//                        String[] ages = commands.get("Between").split(" ");
//                        age2 = Integer.parseInt(ages[0]);
//                        secondAge2 = Integer.parseInt(ages[1]);
//                        users.stream()
//                                .filter(c -> c.getAge() > age2 && c.getAge() < secondAge2)
//                                .filter(c -> c.getRole() == Role.STREAMER)
//                                .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                                .forEach(System.out::println);
//                    } else if (commands.containsKey("At the age")) {
//                        age2 = Integer.parseInt(commands.get("At the age"));
//                        users.stream()
//                                .filter(c -> c.getAge() == age2)
//                                .filter(c -> c.getRole() == Role.STREAMER)
//                                .map(c -> "Login: " + c.getLogin() + " Password: " + c.getPassword() + " Age: " + c.getAge() + " Role: " + c.getRole())
//                                .forEach(System.out::println);
//                    }
//                }
//
//                break;
//            default:
//
//                break;
//        }
//    }

}


