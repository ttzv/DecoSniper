package decos;

import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Legend - Deco(ID, Rarity)
 */
public enum Deco {

    Antiblast("Antiblast", "Blast Resistance", 3, 4, 5, 1),
    Antidote("Antidote", "Poison Resistance", 3, 4, 5, 2),
    Antipara("Antipara", "Paralysis Resistance", 3, 4, 5, 3),
    Artillery("Artillery", "Artillery ", 3, 124, 8, 4),
    Attack("Attack", "Attack Boost", 7, 64, 7, 5),
    Blast("Blast", "Blast Attack", 3, 64, 7, 6),
    Blastcoat("Blastcoat", "Blast Functionality", 1, 64, 7, 7),
    Blaze("Blaze", "Fire Attack", 5, 8, 6, 8),
    Bolt("Bolt", "Thunder Attack ", 5, 8, 6, 9),
    Bomber("Bomber", "Bombadier", 3, 8, 6, 10),
    Botany("Botany", "Botanist", 4, 4, 5, 11),
    Brace("Brace", "Flinch Free", 3, 8, 6, 12),
    Challenger("Challenger", "Agitator", 5, 64, 7, 13),
    Charger("Charger", "Focus", 3, 124, 8, 14),
    Crisis("Crisis", "Resuscitate", 1, 4, 5, 15),
    Critical("Critical", "Critical Boost", 3, 124, 8, 16),
    DefLock("Def Lock", "Iron Skin", 3, 4, 5, 17),
    Defense("Defense", "Defense Boost", 7, 8, 6, 18),
    Destroyer("Destroyer", "Partbreaker", 3, 8, 6, 19),
    Dragon("Dragon", "Dragon Attack", 5, 8, 6, 20),
    DragonRes("Dragon Res", "Dragon Resistance", 3, 4, 5, 21),
    Dragonseal("Dragonseal", "Elderseal Boost", 1, 64, 7, 22),
    Drain("Drain", "Stamina Thief", 3, 8, 6, 23),
    Draw("Draw", "Critical Draw", 3, 124, 8, 24),
    Earplug("Earplug", "Earplugs", 5, 8, 6, 25),
    Elementless("Elementless", "Non-Elemental Boost", 1, 8, 6, 26),
    Enduring("Enduring", "Item Prolonger", 3, 8, 6, 27),
    Enhancer("Enhancer", "Power Prolonger", 3, 64, 7, 28),
    Evasion("Evasion", "Evade Window ", 5, 8, 6, 29),
    Expert("Expert", "Critical Eye", 7, 8, 6, 30),
    FireRes("Fire Res", "Fire Resistance", 3, 4, 5, 31),
    Flawless("Flawless", "Peak Performance", 3, 64, 7, 32),
    Flight("Flight", "Airborne", 1, 4, 5, 33),
    Footing("Footing", "Tremor Resistance", 3, 8, 6, 34),
    Forceshot("Forceshot", "Normal Shots", 1, 64, 7, 35),
    Fortitude("Fortitude", "Fortify", 1, 4, 5, 36),
    Friendship("Friendship", "Wide-Range", 5, 8, 6, 37),
    Frost("Frost", "Ice Attack", 5, 8, 6, 38),
    Fungiform("Fungiform", "Mushroomancer", 3, 124, 8, 39),
    Furor("Furor", "Resentment", 5, 64, 7, 40),
    Geology("Geology", "Geologist", 3, 4, 5, 41),
    Gobbler("Gobbler", "Speed Eating", 3, 8, 6, 42),
    Grinder("Grinder", "Speed Sharpening", 3, 8, 6, 43),
    Handicraft("Handicraft", "Handicraft", 5, 124, 8, 44),
    HeavyArtillery("Heavy Artillery", "Heavy Artillery ", 2, 4, 5, 45),
    Hungerless("Hungerless", "Hunger Resistance", 3, 4, 5, 46),
    IceRes("Ice Res", "Ice Resistance", 3, 4, 5, 47),
    Intimidator("Intimidator", "Intimidator", 3, 4, 5, 48),
    Ironwall("Ironwall", "Guard", 5, 64, 7, 49),
    Jumping("Jumping", "Evade Extender", 3, 8, 6, 50),
    KO("KO", "Slugger", 3, 8, 6, 51),
    Magazine("Magazine", "Capacity Boost", 1, 64, 8, 52),
    Maintenance("Maintenance", "Tool Specialist", 3, 4, 5, 53),
    Medicine("Medicine", "Recovery Up", 3, 8, 6, 54),
    Meowster("Meowster", "Palico Rally", 5, 4, 5, 55),
    Miasma("Miasma", "Effluvia Resistance", 3, 4, 5, 56),
    Mighty("Mighty", "Maximum Might", 3, 64, 7, 57),
    MightyBow("Mighty Bow", "Bow Charge Plus", 1, 124, 8, 58),
    MindsEye("Mind's Eye", "Mind's Eye/Ballistic", 1, 124, 8, 59),
    Mirewalker("Mirewalker", "Aquatic Expert", 3, 4, 5, 60),
    Paracoat("Paracoat", "Paralysis Functionality ", 1, 64, 7, 61),
    Paralyzer("Paralyzer", "Paralysis Attack", 3, 64, 7, 62),
    Pep("Pep", "Sleep Resistance", 3, 4, 5, 63),
    Physique("Physique", "Constitution", 5, 8, 6, 64),
    Pierce("Pierce", "Piercing Shots", 1, 124, 8, 65),
    Poisoncoat("Poisoncoat", "Poison Functionality", 1, 8, 6, 66),
    Potential("Potential", "Heroics", 5, 64, 7, 67),
    Protection("Protection", "Divine Blessing", 3, 4, 5, 68),
    Recovery("Recovery", "Recovery Speed", 3, 8, 6, 69),
    Refresh("Refresh", "Stamina Surge", 3, 64, 7, 70),
    Release("Release", "Free Elem/Ammo Up", 3, 124, 8, 71),
    Resistor("Resistor", "Blight Resistance ", 3, 8, 5, 72),
    Satiated("Satiated", "Free Meal", 1, 4, 5, 73),
    Scent("Scent", "Scenthound", 1, 4, 5, 74),
    Sharp("Sharp", "Protective Polish", 1, 64, 7, 75),
    Sheath("Sheath", "Quick Sheath", 3, 8, 6, 76),
    Shield("Shield", "Guard Up", 1, 64, 7, 77),
    Sleep("Sleep", "Sleep Attack", 3, 64, 7, 78),
    Sleepcoat("Sleepcoat", "Sleep Functionality", 1, 64, 7, 79),
    Slider("Slider", "Affinity Sliding", 1, 8, 6, 80),
    Smoke("Smoke", "Sporepuff Expert", 3, 4, 5, 81),
    Sonorous("Sonorous", "Horn Maestro", 1, 8, 6, 82),
    Specimen("Specimen", "Entomologist", 3, 4, 5, 83),
    Spread("Spread", "Spread/Power Shots", 1, 124, 8, 84),
    Sprinter("Sprinter", "Marathon Runner", 3, 124, 8, 85),
    Steadfast("Steadfast", "Stun Resistance", 3, 8, 6, 86),
    Stonethrower("Stonethrower", "Slinger Capacity", 1, 8, 6, 87),
    Stream("Stream", "Water Attack ", 5, 8, 6, 88),
    Suture("Suture", "Bleeding Resistance", 3, 4, 5, 89),
    Tenderizer("Tenderizer", "Weakness Exploit", 3, 8, 6, 90),
    Throttle("Throttle", "Latent Power ", 5, 64, 7, 91),
    ThunderRes("Thunder Res", "Thunder Resistance", 3, 4, 5, 92),
    TipToe("Tip Toe", "Stealth", 3, 4, 5, 93),
    Trueshot("Trueshot", "Special Ammo Boost ", 3, 64, 7, 94),
    Venom("Venom", "Poison Attack", 3, 8, 6, 95),
    Vitality("Vitality", "Health Boost", 3, 8, 6, 96),
    WaterRes("Water Res", "Water Resistance", 3, 4, 5, 97),
    WindResist("Wind Resist", "Windproof", 5, 8, 6, 98),
    Empty("Empty", "Empty", 0, 0, 0, 0);


    private final String name;
    private final String nameSkill;
    private final int maxLv;
    private final int meldPts;
    private final int rarity;
    private final int id;


    Deco(String name, String nameSkill, int maxLv, int meldPts, int rarity, int id) {
        this.name = name;
        this.nameSkill = nameSkill;
        this.maxLv = maxLv;
        this.meldPts = meldPts;
        this.rarity = rarity;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getNameSkill() {
        return nameSkill;
    }

    public int getMaxLv() {
        return maxLv;
    }

    public int getMeldPts() {
        return meldPts;
    }

    public int getRarity() {
        return rarity;
    }

    public int getId() {
        return id;
    }

    public Color decoColor(){
        switch (getRarity()){
            case 5:
                return Color.LIGHTGREEN;

            case 6:
                return Color.LIGHTBLUE;

            case 7:
                return Color.DARKBLUE;

            case 8:
                return Color.ORANGE;

            default:
                break;
        }
        return null;
    }

    public static ArrayList<String> getDecosNameList() {
        ArrayList<String> decoNameList = new ArrayList<>();
        for (Deco d : values()){
            decoNameList.add(d.getName());
        }
        return decoNameList;
    }

    public static Deco getDecoByID(int id){
        for (Deco d : values()){
            if(d.getId() == id){
                return d;
            }
        }
        return null;
    }

    public static Deco getDecoByName(String name){
        for (Deco d : values()){
            if(d.getName().equals(name)){
                return d;
            }
        }
        return null;
    }

    public static final ArrayList<Deco> getDecosList() {
        ArrayList<Deco> decoList = new ArrayList<>();
        for (Deco d : values()){
            decoList.add(d);
        }
        return decoList;
    }

    public static final ArrayList<Deco> getDefaultValuableDecos(){
        ArrayList<Deco> decoList = new ArrayList<>();
        for (Deco d : values()){
            if(d.getRarity() == 8){
                decoList.add(d);
            }
        }
        return decoList;
    }

    public static final ArrayList<Integer> listDecoToId(ArrayList<Deco> decoList){
        ArrayList<Integer> idList= new ArrayList<>();

        for (Deco d : decoList){
            idList.add(d.getId());
        }

        return idList;
    }

    public static final ArrayList<Deco> listIdToDeco(ArrayList<Integer> idList){
        ArrayList<Deco> decoList = new ArrayList<>();
        for (Integer i : idList){
            decoList.add(getDecoByID(i));
        }
        return decoList;
    }


}
