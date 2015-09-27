
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Scott
 */
public class TextWasteland {

    static int randNum(int n, int a) {
        int randNum = (int) (Math.random() * (n - a + 1)) + a;
        return randNum;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int health = 100;
        String overallRep = "Neutral";
        int n = 10;
        int a = 1;
        Scanner input = new Scanner(System.in);
        String shadowpeakRep = "Neutral";
        String weapon = "";
        String armor = "";
        int cash = 700;


        System.out.println("???: Hi there! What's your name wanderer?");
        System.out.print("You: My name is ");
        String name = input.nextLine();
        System.out.println("???: Well " + name + " welcome to Shadowpeak. What are you here for?");
        System.out.println("1. None of your damn business old man.");
        System.out.println("2. Just passing through.");
        System.out.println("3. I'm here for the shops.");
        System.out.println("4. Here to blow your brains out.");
        System.out.println("Please enter the number for the dialogue option you'd like");
        int shadowpeakDialogue = input.nextInt();
        if (shadowpeakDialogue == 1) {
            System.out.println();
            System.out.print("???: I was just trying to be polite mister. That may be normal talk from where you're from ");
            System.out.print("but around here we don't take kindly to such rude people");
            shadowpeakRep = "Shunned";
        }
        if (shadowpeakDialogue == 2) {
            System.out.print("???: Well I hope you stay for a little while, you should at least check out ");
            System.out.print("'Up in Arms', it's the finest gun store in all of new Michigan.");
        }
        if (shadowpeakDialogue == 3) {
            System.out.println("???: Well be sure to check out 'Up in Arms', it's the finest store in new Michigan");
        }

        if (shadowpeakDialogue == 4) {
            System.out.println("???: You best be jokin' boy. I may not look like much but I can sure draw faster than you can");
            System.out.println("1. Attack ???");
            System.out.println("2. Back down");
            int fightBen = input.nextInt();
            if (fightBen == 1) {
                int randBattle = randNum(n, a);
                if (randBattle == 1 || randBattle == 6) {
                    System.out.println("Ben draws his pistol, but you are able to disarm him.");
                    System.out.println("You then shoot him in the head");
                    shadowpeakRep = "Villain";
                }
                if (randBattle != 1 && randBattle != 6) {
                    System.out.println("Ben draws his pistol and blows your head clean off");
                    int death = randNum(n, a);
                    if (death < 5) {
                        System.out.println("Your carcass serves as a plentiful meal for the vultures");
                        System.exit(0);
                    }
                    if (death > 5) {
                        System.out.println("You are buried in an unmarked grave, forgotten in time.");
                        System.exit(0);
                    }
                }
            }
            if (fightBen == 2) {
                System.out.println("???: That's what I thought.");
                shadowpeakRep = "Thug";
            }
        }
        System.out.println();
        System.out.println("Ben: The people around here call me old ben, but you can just call me ben");
        System.out.println("1. Nice to meet you ben, I should be on my way now.");
        System.out.println("2. Could you direct me to one of the shops?");
        System.out.println("3. Like I give a damn.");
        int shadowpeakDialogue2 = input.nextInt();
        if (shadowpeakDialogue2 == 1) {
            System.out.println("Ben: Take care now!");
            System.out.println("You move on down the road to the next town.");
        }
        if (shadowpeakDialogue2 == 2) {
            System.out.println("Ben: Well which one are you looking for? ");
            System.out.println("1. 'Up in arms' (Guns)");
            System.out.println("2. 'Army Armory' (Clothes/Protection)");
            int shop = input.nextInt();
            if (shop == 1) {
                System.out.println("Shop Keep: Hi there stranger, what can I interest you in?");
                System.out.println();
                System.out.println("You have " + cash + " copper pieces");
                System.out.println("1. 9mm pistol (500 copper)");
                System.out.println("2. AK91 Assult rifle (700 copper)");
                System.out.println("3. 50mm Sniper Rifle (5,000 copper)");
                System.out.println("4. Exit Shop");
                int exit = 0;
                while (weapon.equals("") || exit == 0) {
                    int gunBuy = input.nextInt();
                    if (gunBuy == 1 && cash > 499) {
                        cash = cash - 500;
                        weapon = "9mm pistol";
                    } else if (gunBuy == 1 && cash < 499) {
                        System.out.println("Shopkeep: You don't have enough!");
                    }
                    if (gunBuy == 2 && cash > 699) {
                        cash = cash - 700;
                        weapon = "AK91 Assult rifle";
                    } else if (gunBuy == 2 && cash < 699) {
                        System.out.println("Shopkeep: You don't have enough!");
                    }
                    if (gunBuy == 3 && cash > 4999) {
                        cash = cash - 5000;
                        weapon = "50mm Sniper Rifle";
                    } else if (gunBuy == 3 && cash < 4999) {
                        System.out.println("Shopkeep: You don't have enough!");
                    }
                    if (gunBuy == 4) {
                        exit = 1;
                        System.out.println("Shopkeep: Have a good day now!");
                    }
                }
                System.out.println("Do you want to go to the armor shop too?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                int shopsShadow = input.nextInt();
                if (shopsShadow == 1) {
                    shop = 2;
                }
            }
            if (shop == 2) {
                System.out.println("Shop Keep: Hi there stranger, what can I interest you in?");
                System.out.println("You have " + cash + " copper pieces");
                System.out.println("1. Leather Jacket (100 copper)");
                System.out.println("2. Armored Jacket (250 copper)");
                System.out.println("3. Hockey pads (350 copper)");
                System.out.println("4. Exit shop");
                int armorShopShadowExit = 0;
                while (armor.equals("") || armorShopShadowExit == 0) {
                    int armorShopShadow = input.nextInt();
                    if (armorShopShadow == 1 && cash > 99) {
                        cash = cash - 100;
                        armor = "Leather Jacket";
                    } else if (armorShopShadow == 1 && cash < 99) {
                        System.out.println("Shopkeep: You don't have enough copper!");
                    }
                    if (armorShopShadow == 2 && cash > 249) {
                        cash = cash - 250;
                        armor = "Armored jacket";
                    } else if (armorShopShadow == 2 && cash < 249) {
                        System.out.println("Shopkeep: You don't have enough copper!");
                    }
                    if (armorShopShadow == 3 && cash > 349) {
                        cash = cash - 350;
                        armor = "Hockey Pads";
                    } else if (armorShopShadow == 3 && cash < 349) {
                        System.out.println("Shopkeep: You don't have enough copper!");
                    }
                    if (armorShopShadow == 4) {
                        armorShopShadowExit = armorShopShadowExit + 1;
                        System.out.println("Shopkeep: Thanks for dropping by!");
                        break;
                    }
                }
            }
        }
        if (shadowpeakDialogue2 == 3) {
            System.out.println("Ben: I'm really getting tired of you, get out of here.");
            System.out.println("You move on down the road in the dirstion of the next town.");
        }
        System.out.println("After walking for what feels like hours you come to a fork in the road");
        System.out.println("1. Left");
        System.out.println("2. Right");
        int shadowTravel = input.nextInt();
        if (shadowTravel == 1) {
            System.out.println("You travel on the left road towards an unknown town you see in the horizon");
            System.out.println();
            System.out.println("You get closer to the town and you hear yelling and screeming.");
            System.out.println("1. Turn around and go on the left road");
            System.out.println("2. Investigate the commotion");
            int shadyTown = input.nextInt();
            if (shadyTown == 1) {
                shadowTravel = 2;
            }
            if (shadyTown == 2) {
                System.out.println("As you get closer you see bandits rounding up people in the town square.");
                System.out.println("1. Attack bandits/Rescue townspeople");
                System.out.println("2. Join bandits");
                System.out.println("3. Leave");
                int shadyTownRes = input.nextInt();
                if (shadyTownRes == 1) {
                    if (weapon.equals("9mm pistol")) {
                        n = 8;
                    }
                    if (weapon.equals("AK91 Assult rifle")) {
                        n = 5;
                    }
                    if (weapon.equals("50mm Sniper Rifle")) {
                        n = 3;
                    }
                    if (weapon.equals("")) {
                        n = 15;
                    }
                    if (armor.equals("Leather Jacket")) {
                        a = 2;
                    }
                    if (armor.equals("Armored jacket")) {
                        a = 3;
                    }
                    if (armor.equals("Hockey Pads")) {
                        a = 5;
                    }
                    int townTakeover = randNum(n, a);
                    int remainder = townTakeover / 2;
                    remainder = townTakeover % 2;
                    if (remainder == 0) {
                        System.out.println("With skill you take out the bandits one by one, saving the town");
                        System.out.println("You are rewarded with 2000 copper!");
                        cash = cash + 2000;
                        System.out.println("You now have " + cash + " copper");
                        overallRep = "Hero";
                        System.out.println("Overall reputation: " + overallRep);
                        System.out.println("The people of the wastes have heard of you heroic acts, and are more trusting of you");
                        n = 10;
                        a = 1;
                        shadowTravel = 2;
                        System.out.println("You travel back to the right road");
                    }
                    if (remainder != 0) {
                        System.out.println("You are lacking in equipment and skill to take on the bandits.");
                        System.out.println();
                        System.out.println("The bandits overwhelm you, and you are executed along with the townspeople.");
                        n = 10;
                        a = 1;
                        int death = randNum(n, a);
                        if (death < 5) {
                            System.out.println("Your carcass serves as a plentiful meal for the vultures");
                            System.exit(0);
                        }
                        if (death > 5) {
                            System.out.println("You are buried in an unmarked grave, forgotten in time.");
                            System.exit(0);
                        }
                    }
                }
                if (shadyTownRes == 2) {
                    overallRep = "Anarchist";
                    System.out.println("Overall reputation: " + overallRep);
                    System.out.println("People have heard of your crimes, you'd be lucky to be allowed in any settlement at all.");
                    System.out.println();
                }
                if (shadyTownRes == 3) {
                    overallRep = "Unfeeling";
                    System.out.println("Overall reputation: " + overallRep);
                    System.out.println("You're kind of a bastard");
                    shadowTravel = 2;
                }
            }

        }
        if (shadowTravel == 2) {
            System.out.println("You travel on the right road towards a large tower in the distance");
            System.out.println("You come across a small rest stop");
            System.out.println();
            System.out.println("1. Stop at shop");
            System.out.println("2. Keep Going");
            int stop = input.nextInt();
            if(stop == 1) {
                System.out.println("Shopkeep: Welcome to teh roadside stop!");
                System.out.println("Shopkeep: What can I do you for?");
                System.out.println("1. AK91 Assult rifle (700 copper)");
                System.out.println("2. 50mm Sniper Rifle (5,000 copper)");
                System.out.println("3. 'What's with that huge tower in the distance?'");
                System.out.println("4. Exit Shop");
                int roadSide = input.nextInt();
                if (roadSide == 1 && cash > 699) {
                        cash = cash - 700;
                        weapon = "AK91 Assult rifle";
                    } else if (roadSide == 1 && cash < 699) {
                        System.out.println("Shopkeep: You don't have enough!");
                    }
                    if (roadSide == 2 && cash > 4999) {
                        cash = cash - 5000;
                        weapon = "50mm Sniper Rifle";
                    } else if (roadSide == 2 && cash < 4999) {
                        System.out.println("Shopkeep: You don't have enough!");
                    }
                    if(roadSide == 3) {
                        
                    }
                    if(roadSide == 4 || roadSide > 4) {
                        System.out.println("Shopkeep: Take care now!");
                    }
                System.out.println("You have " + cash + " copper");
            }
            System.out.println("You continue down the road");
            
        }


    }
}
