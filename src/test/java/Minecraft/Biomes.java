package Minecraft;

public class Biomes {
    
    static final int FOREST_BIOME =				 4;
    static final int SWAMPLAND_BIOME =			 6;
    static final int FOREST_HILLS_BIOME	=		18;
    static final int BIRCH_FOREST_BIOME	=		27;
    static final int BIRCH_FOREST_HILLS_BIOME =	28;
    static final int ROOFED_FOREST_BIOME =		29;

    static final int MESA_BIOME =					37;
    static final int MESA_PLATEAU_F_BIOME =		38;
    static final int MESA_PLATEAU_BIOME	=		39;

    static class Biome {
        String name;
        float temperature;
        float rainfall;
        int grass;
        int foliage;

        Biome(String name, float temp, float rain, int grass, int foliage) {
            this.name = name;
            this.temperature = temp;
            this.rainfall = rain;
            this.grass = grass;
            this.foliage = foliage;
        }
    }

    static class BiomeCorner {
        int red;
        int green;
        int blue;

        BiomeCorner(int red, int green, int blue) {
            this.red = red;
            this.blue = blue;
            this.green = green;
        }
    }

    static Biome[] gBiomes = {	// IMPORTANT: do not change 256 size here.
        //    ID    Name             Temperature, rainfall, grass, foliage colors
        //                                                  - note: the colors here are just placeholders, they are computed in the program
        new Biome( /*   0 */ "Ocean",					0.5f, 0.5f, 0x92BD59, 0x77AB2F ),	// default values of temp and rain
        new Biome( /*   1 */ "Plains",					0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*   2 */ "Desert",					2.0f, 0.0f, 0x92BD59, 0x77AB2F ),
        new Biome( /*   3 */ "Mountains",				0.2f, 0.3f, 0x92BD59, 0x77AB2F ),
        new Biome( /*   4 */ "Forest",					0.7f, 0.8f, 0x92BD59, 0x77AB2F ),
        new Biome( /*   5 */ "Taiga",				   0.25f, 0.8f, 0x92BD59, 0x77AB2F ),
        new Biome( /*   6 */ "Swamp",					0.8f, 0.9f, 0x92BD59, 0x77AB2F ),
        new Biome( /*   7 */ "River",					0.5f, 0.5f, 0x92BD59, 0x77AB2F ),	// default values of temp and rain
        new Biome( /*   8 */ "Nether",					2.0f, 0.0f, 0x92BD59, 0x77AB2F ),
        new Biome( /*   9 */ "The End",					0.5f, 0.5f, 0x92BD59, 0x77AB2F ),	// default values of temp and rain
        new Biome( /*  10 */ "Frozen Ocean",				0.0f, 0.5f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  11 */ "Frozen River",				0.0f, 0.5f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  12 */ "Snowy Tundra",				0.0f, 0.5f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  13 */ "Snowy Mountains",			0.0f, 0.5f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  14 */ "Mushroom Fields",			0.9f, 1.0f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  15 */ "Mushroom Field Shore",		0.9f, 1.0f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  16 */ "Beach",					0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  17 */ "Desert Hills",				2.0f, 0.0f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  18 */ "Wooded Hills",				0.7f, 0.8f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  19 */ "Taiga Hills",			   0.25f, 0.8f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  20 */ "Mountain Edge",			0.2f, 0.3f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  21 */ "Jungle",				   0.95f, 0.9f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  22 */ "Jungle Hills",			   0.95f, 0.9f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  23 */ "Jungle Edge",			   0.95f, 0.8f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  24 */ "Deep Ocean",				0.5f, 0.5f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  25 */ "Stone Shore",				0.2f, 0.3f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  26 */ "Snowy Beach",			   0.05f, 0.3f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  27 */ "Birch Forest",				0.6f, 0.6f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  28 */ "Birch Forest Hills",		0.6f, 0.6f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  29 */ "Dark Forest",				0.7f, 0.8f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  30 */ "Snowy Taiga",			   -0.5f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  31 */ "Snowy Taiga Hills",	   -0.5f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  32 */ "Giant Tree Taiga",			0.3f, 0.8f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  33 */ "Giant Tree Taiga Hills",	0.3f, 0.8f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  34 */ "Wooded Mountains",			0.2f, 0.3f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  35 */ "Savanna",					1.2f, 0.0f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  36 */ "Savanna Plateau",			1.0f, 0.0f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  37 */ "Badlands",					2.0f, 0.0f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  38 */ "Wooded Badlands Plateau",	2.0f, 0.0f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  39 */ "Badlands Plateau",			2.0f, 0.0f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  40 */ "Small End Islands",		0.5f, 0.5f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  41 */ "End Midlands",				0.5f, 0.5f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  42 */ "End Highlands",			0.5f, 0.5f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  43 */ "End Barrens",				0.5f, 0.5f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  44 */ "Warm Ocean",				0.8f, 0.5f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  45 */ "Lukewarm Ocean",			0.8f, 0.5f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  46 */ "Cold Ocean",				0.8f, 0.5f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  47 */ "Deep Warm Ocean",			0.8f, 0.5f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  48 */ "Deep Lukewarm Ocean",		0.8f, 0.5f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  49 */ "Deep Cold Ocean",			0.8f, 0.5f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  50 */ "Deep Frozen Ocean",		0.8f, 0.5f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  51 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  52 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  53 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  54 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  55 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  56 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  57 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  58 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  59 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  60 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  61 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  62 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  63 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  64 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  65 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  66 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  67 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  68 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  69 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  70 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  71 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  72 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  73 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  74 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  75 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  76 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  77 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  78 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  79 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  80 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  81 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  82 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  83 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  84 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  85 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  86 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  87 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  88 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  89 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  90 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  91 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  92 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  93 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  94 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  95 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  96 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  97 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  98 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /*  99 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 100 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 101 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 102 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 103 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 104 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 105 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 106 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 107 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 108 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 109 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 110 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 111 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 112 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 113 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 114 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 115 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 116 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 117 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 118 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 119 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 120 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 121 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 122 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 123 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 124 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 125 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 126 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 127 */ "The Void",            		    0.5f, 0.5f, 0x92BD59, 0x77AB2F ),	// default values of temp and rain; also, no height differences
        new Biome( /* 128 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 129 */ "Sunflower Plains",			    0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 130 */ "Desert Lakes",					2.0f, 0.0f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 131 */ "Gravelly Mountains",		    0.2f, 0.3f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 132 */ "Flower Forest",				0.7f, 0.8f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 133 */ "Taiga Mountains",			   0.25f, 0.8f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 134 */ "Swamp Hills",					0.8f, 0.9f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 135 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 136 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 137 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 138 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 139 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 140 */ "Ice Spikes",				    0.0f, 0.5f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 141 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 142 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 143 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 144 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 145 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 146 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 147 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 148 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 149 */ "Modified Jungle",			   0.95f, 0.9f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 150 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 151 */ "Modified Jungle Edge",		   0.95f, 0.8f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 152 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 153 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 154 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 155 */ "Tall Birch Forest",		    0.6f, 0.6f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 156 */ "Tall Birch Hills",				0.6f, 0.6f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 157 */ "Dark Forest Hills",		    0.7f, 0.8f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 158 */ "Snowy Taiga Mountains",	   -0.5f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 159 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 160 */ "Giant Spruce Taiga",		   0.25f, 0.8f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 161 */ "Giant Spruce Taiga Hills",    0.25f, 0.8f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 162 */ "Gravelly Mountains+",			0.2f, 0.3f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 163 */ "Shattered Savanna",			1.1f, 0.0f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 164 */ "Shattered Savanna Plateau",    1.0f, 0.0f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 165 */ "Eroded Badlands",				2.0f, 0.0f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 166 */ "Modified Wooded Badlands Plateau",    2.0f, 0.0f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 167 */ "Modified Badlands Plateau",    2.0f, 0.0f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 168 */ "Bamboo Jungle",			   0.95f, 0.9f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 168 */ "Bamboo Jungle Hills",		   0.95f, 0.9f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 170 */ "Soul Sand Valley",				2.0f, 0.0f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 171 */ "Crimson Forest",				2.0f, 0.0f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 172 */ "Warped Forest",				2.0f, 0.0f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 173 */ "Basalt Deltas",				2.0f, 0.0f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 174 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 175 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 176 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 177 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 178 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 179 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 180 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 181 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 182 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 183 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 184 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 185 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 186 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 187 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 188 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 189 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 190 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 191 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 192 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 193 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 194 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 195 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 196 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 197 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 198 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 199 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 200 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 201 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 202 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 203 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 204 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 205 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 206 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 207 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 208 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 209 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 210 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 211 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 212 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 213 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 214 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 215 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 216 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 217 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 218 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 219 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 220 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 221 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 222 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 223 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 224 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 225 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 226 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 227 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 228 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 229 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 230 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 231 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 232 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 233 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 234 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 235 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 236 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 237 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 238 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 239 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 240 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 241 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 242 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 243 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 244 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 245 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 246 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 247 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 248 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 249 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 250 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 251 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 252 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 253 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 254 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
        new Biome( /* 255 */ "Unknown Biome",				0.8f, 0.4f, 0x92BD59, 0x77AB2F ),
    };

    static BiomeCorner[] grassCorners =
    {
        new BiomeCorner(191, 183,  85 ),	// lower left, temperature starts at 1.0 on left
        new BiomeCorner(128, 180, 151 ),	// lower right
        new BiomeCorner(71, 205,  51 )	// upper left
    };

    static BiomeCorner[] foliageCorners =
    {
        new BiomeCorner(174, 164,  42 ),	// lower left, temperature starts at 1.0 on left
        new BiomeCorner(96, 161, 123 ),	// lower right
        new BiomeCorner(26, 191,  0 )	// upper left
    };


    // NOTE: elevation is number of meters above a height of 64. If elevation is < 64, pass in 0.
    static int biomeColor(float temperature, float rainfall, int elevation, BiomeCorner[] corners) {
        // get UVs
        temperature = clamp(temperature - (float)elevation * 0.00166667f, 0.0f, 1.0f);
        // crank it up: temperature = clamp(temperature - (float)elevation*0.166667f,0.0f,1.0f);
        rainfall = clamp(rainfall, 0.0f, 1.0f);
        rainfall *= temperature;

        // UV is essentially temperature, rainfall

        // lambda values for barycentric coordinates
        float[] lambda = new float[3];
        lambda[0] = temperature - rainfall;
        lambda[1] = 1.0f - temperature;
        lambda[2] = rainfall;

        float red = 0.0f, green = 0.0f, blue = 0.0f;
        for (int i = 0; i < 3; i++) {
            red += lambda[i] * corners[i].red;
            green += lambda[i] * corners[i].green;
            blue += lambda[i] * corners[i].blue;
        }

        int r = (int)clamp(red, 0.0f, 255.0f);
        int g = (int)clamp(green, 0.0f, 255.0f);
        int b = (int)clamp(blue, 0.0f, 255.0f);

        return (r << 16) | (g << 8) | b;
    }

    static int biomeGrassColor(float temperature, float rainfall, int elevation) {
        return biomeColor(temperature, rainfall, elevation, grassCorners);
    }

    static int biomeFoliageColor(float temperature, float rainfall, int elevation) {
        return biomeColor(temperature, rainfall, elevation, foliageCorners);
    }

    void precomputeBiomeColors() {
        for (int biome = 0; biome < 256; biome++) {
            gBiomes[biome].grass = computeBiomeColor(biome, 0, true);
            gBiomes[biome].foliage = computeBiomeColor(biome, 0, false);
        }
    }

    // elevation == 0 means for precomputed colors and for elevation off
    // or 64 high or below.
    static int computeBiomeColor(int biome, int elevation, boolean isGrass) {
        switch (biome & 0x7) {
            case SWAMPLAND_BIOME:
                // the fefefe makes it so that carries are copied to the low bit,
                // then their magic "go to green" color offset is added in, then
                // divide by two gives a carry that will nicely go away.
                // old method:
                //color = BiomeGrassColor( gBiomes[biome].temperature, gBiomes[biome].rainfall );
                //gBiomes[biome].grass = ((color & 0xfefefe) + 0x4e0e4e) / 2;
                //color = BiomeFoliageColor( gBiomes[biome].temperature, gBiomes[biome].rainfall );
                //gBiomes[biome].foliage = ((color & 0xfefefe) + 0x4e0e4e) / 2;

                // new method:
                // yes, it's hard-wired in. It actually varies with temperature:
                //         return temperature < -0.1D ? 0x4c763c : 0x6a7039;
                // where temperature is varied by PerlinNoise, but I haven't recreated the
                // PerlinNoise function yet. Rich green vs. sickly swamp brown. I'm going with brown.
                return 0x6a7039;

            // These are actually perfectly normal. Only sub-type 3, roofed forest, is different.
            //case FOREST_BIOME:	// forestType 0
            //case FOREST_HILLS_BIOME:	// forestType 0
            //case BIRCH_FOREST_BIOME:	// forestType 2
            //case BIRCH_FOREST_HILLS_BIOME:	// forestType 2
            //	break;

            case ROOFED_FOREST_BIOME:	// forestType 3
                if (isGrass) {
                    int color = biomeGrassColor(gBiomes[biome].temperature, gBiomes[biome].rainfall, elevation);
                    // the fefefe makes it so that carries are copied to the low bit,
                    // then their magic "go to green" color offset is added in, then
                    // divide by two gives a carry that will nicely go away.
                    return ((color & 0xfefefe) + 0x28340a) / 2;
                }
                else {
                    return biomeFoliageColor(gBiomes[biome].temperature, gBiomes[biome].rainfall, elevation);
                }

            case MESA_BIOME:
            case MESA_PLATEAU_F_BIOME:
            case MESA_PLATEAU_BIOME:
                // yes, it's hard-wired
                return isGrass ? 0x90814d : 0x9e814d;

            default:
                return isGrass ? biomeGrassColor(gBiomes[biome].temperature, gBiomes[biome].rainfall, elevation) :
                        biomeFoliageColor(gBiomes[biome].temperature, gBiomes[biome].rainfall, elevation);
        }
    }

    static int biomeSwampRiverColor(int color) {
        int r = (int)((color >> 16) & 0xff);
        int g = (int)((color >> 8) & 0xff);
        int b = (int)color & 0xff;

        // swamp color modifier is 0xE0FFAE
        r = (r * 0xE0) / 255;
        // does nothing: g=(g*0xFF)/255;
        b = (b * 0xAE) / 255;
        color = (r << 16) | (g << 8) | b;

        return color;
    }

    public static float clamp(float val, float min, float max) {
        return Math.max(min, Math.min(max, val));
    }


}
