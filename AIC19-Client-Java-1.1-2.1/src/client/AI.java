package client;

import client.model.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.util.Random;

public class AI
{

    private Random random = new Random();

    public void preProcess(World world)
    {
        System.out.println("pre process started");
    }

    public void pickTurn(World world)
    {
        System.out.println("pick started");
        world.pickHero(HeroName.values()[world.getCurrentTurn()]);
        /*
        برای انتخاب رندم :

        int r;
        Random random = new Random();
        r = random.nextInt();
        if(r<0) r *= -1;
        r %= 4;
        world.pickHero(HeroName.values()[r]);
        */
    }

    public void moveTurn(World world)
    {
        System.out.println("move started");
        Hero[] heroes = world.getMyHeroes();

        for (Hero hero : heroes)
        {
            world.moveHero(hero, Direction.values()[random.nextInt(4)]);
        }
    }

//    public void moveTurn(World world,Cell cell,Map map,Hero hero1)
//   {
//        System.out.println("move started");
//        Hero[] heroes = world.getMyHeroes();
//        int Point = 0;
//        int x,y;
//        x = cell.getColumn();   y = cell.getRow();
//        int Distance = Distance_Objective_Zone(cell,map);
//        Point += (1 - hero1.getMoveAPCost()/10)*Distance;
//        Point *= 5/3;
//        Point -= (1 - hero1.getCurrentHP()/hero1.getMaxHP());     // در معرض دید حریف بودن هم باید لحاظ شود!
//
//
//        for (Hero hero : heroes)
//        {
//            world.moveHero(hero, Direction.values()[random.nextInt(4)]);
//        }
//    }

    public void actionTurn(World world) {
        System.out.println("action started");
        Hero[] heroes = world.getMyHeroes();
        Map map = world.getMap();
        for (Hero hero : heroes)
        {
            int row = random.nextInt(map.getRowNum());
            int column = random.nextInt(map.getColumnNum());

            world.castAbility(hero, hero.getAbilities()[random.nextInt(3)], row, column);
        }
    }

    private int Distance_Objective_Zone(Cell cell,Map map)
    {
        int x,y;
        x = cell.getColumn();   y = cell.getRow();
        int distance = Integer.MAX_VALUE;
        int fasele;
        for (int i = 0; i < map.getRowNum(); i++)
        {
            for (int j = 0; j < map.getColumnNum(); j++)
            {
                if(cell.isInObjectiveZone())
                {
                    fasele = Math.abs(x - i) + Math.abs(y - j);
                    if(fasele < distance)   distance = fasele;
                }
            }
        }
        return distance;
    }

}
