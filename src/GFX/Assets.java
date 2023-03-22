package GFX;

import java.awt.image.BufferedImage;


public class Assets {

    public static BufferedImage blankTile,innerTile,outerTile,shotgun,m4,sniper,pistol,grenadeLauncher, knife;
    public static BufferedImage player_torso_right, player_torso_left,player_legs_idle_left,player_legs_idle_right,
            player_torso_crouching_idle_left,player_torso_crouching_idle_right, player_legs_crouching_idle_left,player_legs_crouching_idle_right,
            player_torso_crouching_walking_left,player_torso_crouching_walking_right;
    public static BufferedImage[] button_start;
    public static BufferedImage[] player_running_left, player_running_right;
    public static BufferedImage[] player_legs_crouching_walking_left, player_legs_crouching_walking_right;
    public static BufferedImage player_legs_crouching_inAir_left, player_legs_crouching_inAir_right;
    public static BufferedImage pistol_mirror,m4_mirror,shotgun_mirror,sniper_mirror,grenadeLauncher_mirror;
    public static BufferedImage crate,ammo_shotgun,ammo_sniper,ammo_automaticRifle,ammo_grenadeLauncher;
    public static BufferedImage zombieDog;
    public static BufferedImage round_explosive, round_explosive_mirror;
    public static BufferedImage[] explosionAnimation, slashAnimation;
    public static SpriteSheet explosionSheet;
    public static BufferedImage[] background1;


    public static void init(){
        button_start = new BufferedImage[2];
        button_start[0] = ImageLoader.loadImage("/textures/menu/start_button1.png");
        button_start[1] = ImageLoader.loadImage("/textures/menu/start_button2.png");

        round_explosive = ImageLoader.loadImage("/textures/rounds/round_explosive.png");
        round_explosive_mirror = ImageLoader.loadImage("/textures/rounds/round_explosive_mirror.png");

        background1 = new BufferedImage[1];
        background1[0] = ImageLoader.loadImage("/textures/backgrounds/background.png");
        //background1[1] = ImageLoader.loadImage("/textures/backgrounds/background1.png");



        explosionSheet = new SpriteSheet(ImageLoader.loadImage("/textures/staticAnimations/explosion_spritesheet.png"));
        explosionAnimation = new BufferedImage[7];
        explosionAnimation[0] = explosionSheet.crop(0,0,192,192);
        explosionAnimation[1] = explosionSheet.crop(192,0,192,192);
        explosionAnimation[2] = explosionSheet.crop(2*192,0,192,192);
        explosionAnimation[3] = explosionSheet.crop(3*192,0,192,192);
        explosionAnimation[4] = explosionSheet.crop(4*192,0,192,192);
        explosionAnimation[5] = explosionSheet.crop(0,192,192,192);
        explosionAnimation[6] = explosionSheet.crop(192,192,192,192);

        slashAnimation = new BufferedImage[3];
        slashAnimation[0]  = ImageLoader.loadImage("/textures/staticAnimations/slash1.png");
        slashAnimation[1]  = ImageLoader.loadImage("/textures/staticAnimations/slash2.png");
        slashAnimation[2]  = ImageLoader.loadImage("/textures/staticAnimations/slash3.png");


        addTorso();
        addLegs();


        blankTile = ImageLoader.loadImage("/textures/tiles/blankTile.png");
        innerTile = ImageLoader.loadImage("/textures/tiles/innerTile.png");
        outerTile = ImageLoader.loadImage("/textures/tiles/outerTile.png");

        shotgun = ImageLoader.loadImage("/textures/weapons/shotgun.png");
        shotgun_mirror = ImageLoader.loadImage("/textures/weapons/shotgun_mirror.png");

        pistol = ImageLoader.loadImage("/textures/weapons/pistol.png");
        pistol_mirror = ImageLoader.loadImage("/textures/weapons/pistol_mirror.png");

        sniper = ImageLoader.loadImage("/textures/weapons/sniper.png");
        sniper_mirror=ImageLoader.loadImage("/textures/weapons/sniper_mirror.png");

        m4 = ImageLoader.loadImage("/textures/weapons/m4.png");
        m4_mirror =  ImageLoader.loadImage("/textures/weapons/m4_mirror.png");

        grenadeLauncher = ImageLoader.loadImage("/textures/weapons/grenade_launcher.png");
        grenadeLauncher_mirror = ImageLoader.loadImage("/textures/weapons/grenade_launcher_mirror.png");

        knife = ImageLoader.loadImage("/textures/weapons/knife.png");

        addStaticEntities();
        addEnemies();

    }

    private static void addEnemies(){
        zombieDog = ImageLoader.loadImage("/textures/enemies/zombie_dog.png");
    }

    private static void addTorso(){
        player_torso_right = ImageLoader.loadImage("/textures/player/player_torso_right.png");

        player_torso_left = ImageLoader.loadImage("/textures/player/player_torso_left.png");

        player_torso_crouching_idle_left =  ImageLoader.loadImage("/textures/player/player_torso_crouching_idle_left.png");

        player_torso_crouching_idle_right =  ImageLoader.loadImage("/textures/player/player_torso_crouching_idle_right.png");

        player_torso_crouching_walking_left = ImageLoader.loadImage("/textures/player/player_torso_crouching_walking_left.png");

        player_torso_crouching_walking_right = ImageLoader.loadImage("/textures/player/player_torso_crouching_walking_right.png");
    }
    private static void addLegs(){
        player_legs_idle_left  = ImageLoader.loadImage("/textures/player/player_legs_left1.png");

        player_legs_idle_right = ImageLoader.loadImage("/textures/player/player_legs_right1.png");



        player_legs_crouching_idle_left = ImageLoader.loadImage("/textures/player/player_legs_crouching_idle_left.png");

        player_legs_crouching_idle_right = ImageLoader.loadImage("/textures/player/player_legs_crouching_idle_right.png");



        player_running_left = new BufferedImage[4];
        player_running_left[0] = ImageLoader.loadImage("/textures/player/player_legs_left1.png");
        player_running_left[1] = ImageLoader.loadImage("/textures/player/player_legs_left2.png");
        player_running_left[2] = ImageLoader.loadImage("/textures/player/player_legs_left3.png");
        player_running_left[3] = ImageLoader.loadImage("/textures/player/player_legs_left4.png");

        player_running_right = new BufferedImage[4];
        player_running_right[0] = ImageLoader.loadImage("/textures/player/player_legs_right1.png");
        player_running_right[1] = ImageLoader.loadImage("/textures/player/player_legs_right2.png");
        player_running_right[2] = ImageLoader.loadImage("/textures/player/player_legs_right3.png");
        player_running_right[3] = ImageLoader.loadImage("/textures/player/player_legs_right4.png");

        player_legs_crouching_walking_right = new BufferedImage[1];
        player_legs_crouching_walking_right[0] = ImageLoader.loadImage("/textures/player/player_legs_crouching_walking_right.png");

        player_legs_crouching_walking_left = new BufferedImage[1];
        player_legs_crouching_walking_left[0] = ImageLoader.loadImage("/textures/player/player_legs_crouching_walking_left.png");

        player_legs_crouching_inAir_left = ImageLoader.loadImage("/textures/player/player_legs_crouching_walking_left.png");

        player_legs_crouching_inAir_right = ImageLoader.loadImage("/textures/player/player_legs_crouching_walking_right.png");

    }
    private static void addStaticEntities(){
        crate = ImageLoader.loadImage("/textures/staticEntities/crate.png");
        ammo_shotgun = ImageLoader.loadImage("/textures/staticEntities/ammo_shotgun.png");
        ammo_sniper= ImageLoader.loadImage("/textures/staticEntities/ammo_sniper.png");
        ammo_automaticRifle = ImageLoader.loadImage("/textures/staticEntities/ammo_automaticRifle.png");
        ammo_grenadeLauncher = ImageLoader.loadImage("/textures/staticEntities/ammo_grenadeLauncher.png");
    }
}
