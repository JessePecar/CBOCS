package com.example.cbocs;

import android.database.Cursor;

public class MenuScript {
    public void populateDB(MenuDatabase mD){
        //Clear the existing database, then loop to add all the items.
        mD.onUpgrade(mD.getWritableDatabase(),0,1);

        String[] menuItems = {"Roast Beef", "Country Fried Steak", "Meatloaf", "10oz Rib Eye Steak",
                                "Grilled Sirloin Steak", "CB Sampler SGH","CB Sampler COH", "Half-Pound Hamburger Steak",
                                "Chicken 'n' Dumplins(3)", "Chicken 'n' Dumplins(2)","Grilled Tenders(6)",
                                "Grilled Tenders(4)", "Fried Tenders(6)", "Fried Tenders(4)",
                                "Chicken Fried Chicken", "Apple Cider BBQ Chicken", "Fried Chicken Livers",
                                "Homestyle Chicken", "Signature Fried Chicken", "Country Fried Shrimp",
                                "Fried Catfish(2)", "Fried Catfish(1)", "Grilled Catfish(2)",
                                "Grilled Catfish(1)", "Haddock", "Lemon Pepper Trout(2)",
                                "Lemon Pepper Trout(1)", "Fish Fry", "Pork Chop(1)",
                                "Pork Chop(2)", "DDF Chop", "Sugar Cured Ham",
                                "Country Smoked Ham", "Southern Grilled Chicken Caesar", "Veg Plate(4)",
                                "Veg Plate(6)", "Baked Chicken", "LSP Meatloaf", "Chicken Pot Pie",
                                "Turkey n Dressing(2)", "TUE Trout", "Broccoli Cheddar Chicken",
                                "Chicken and Rice", "Turkey n Dressing(3)"};
        //AMTWRFSU - All, Mon, Tue, Wed, Thu, Fri, Sat, Sun
        String[] avail = {"A", "A","A","A",
                "A","A", "A", "A",
                "A","A","A",
                "A","A","A",
                "A","A","A",
                "U","A","A",
                "A", "A", "A",
                "A", "A", "A",
                "A", "F", "A",
                "A", "A", "A",
                "A", "A", "A",
                "A", "M", "A", "W",
                "R", "A", "W",
                "S", "R" };
        int[] sideCount = {3,3,3,3,
                            3,3,3,2,
                            3, 2, 3,
                            2,3,2,
                            3, 2, 2,
                            2,2, 3,
                            3, 2, 3,
                            2, 3, 3,
                            2, 2, 2,
                            3, 2, 2,
                            2, 0, 4,
                            6, 2, 2, 0,
                            2, 2, 2,
                            2, 3 };
        String[] menuType = {"Fancy Fixin", "Fancy Fixin", "Fancy Fixin", "Fancy Fixin",
                "Fancy Fixin","Fancy Fixin","Fancy Fixin", "Country Dinner",
                "Fancy Fixin", "Country Dinner", "Fancy Fixin",
                "Country Dinner", "Fancy Fixin", "Country Dinner",
                "Fancy Fixin", "Wholesome Fixin", "Country Dinner",
                "Special", "Promotion", "Fancy Fixin",
                "Fancy Fixin", "Country Dinner", "Fancy Fixin",
                "Country Dinner", "Fancy Fixin", "Fancy Fixin",
                "Country Dinner", "Special", "Country Dinner",
                "Fancy Fixin", "Special", "Country Dinner",
                "Country Dinner", "Wholesome Fixin", "Country Dinner",
                "Country Dinner", "Special", "Special", "Special",
                "Special", "Special", "Special",
                "Special", "Special"};

        for(int i = 0; i < menuItems.length; i++){
            mD.addMenuItem(menuItems[i], avail[i], sideCount[i], menuType[i]);
        }
        String[] sides = {"Fried Apples","Homestyle Fries","Fried Okra","Brussel Sprouts 'n' Kayle Salad","Cole Slaw",
                        "Carrots","Hashbrown Casserole","$Loaded Hashbrown Casserole","Broccoli","Corn",
                        "Turnip Greens","Macaroni'n'Cheese","Green Beans","Mash Brown","Mashed Potatoes",
                        "Mashed White","Fruit Cup","Apple Slices","Pinto Beans","Dumplins","$Baked Potato","$Baked Sweet Potato",
                        "Cabbage","Cabbage","Cornbread Dressing", "Cornbread Dressing", "Lima Beans", "Lima Beans", "Lima Beans", "Sweet Potato Casserole"};

        String[] sidesAvail = {"A","A","A","A","A",
                "A","A","A","A","A",
                "A","A","A","A","A",
                "A","A","A","A","A","A","A",
                "U","W","M", "R", "S", "T", "F", "R"};
        for(int i = 0; i < sides.length; i++){
            mD.addMenuItem(sides[i], sidesAvail[i], 0, "Sides");
        }
        String[] bread = {"Biscuit","Cornbread","Sourdough","Sourdough Grilled","Sourdough Toasted", "Sourdough Seared",
                "Multigrain","Multigrain grilled","Multigrain toasted","Multigrain seared","Rye", "Rye grilled", "Rye toasted", "Rye seared",
                "English Muffin","English Muffin grilled","English Muffin toasted","English Muffin seared","White", "White grilled", "White toasted", "White seared"};

        for(int i = 0; i < bread.length; i++){
            mD.addMenuItem(bread[i], "A", 0, "Bread");
        }
    }
}
