package isen.content;

import arc.graphics.*;
import arc.struct.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.part.DrawPart.PartProgress;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.environment.OreBlock;
import mindustry.world.blocks.production.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;
import isen.world.blocks.environment.*;



import static mindustry.type.ItemStack.*;
public class IsenBlocks{
    public static Block 
    // Turrets
    duo, scorch, hail, arc, lancer, scatter, wave, parallax, swarmer, salvo,
    segment, tsunami, fuse, ripple, cyclone, foreshadow, spectre, meltdown,
    // Drills
    mechanicalDrill, pneumaticDrill, laserDrill, blastDrill,
    // Pumps
    mechanicalPump, 
    // Factories

    // Ores
    oreCopper, oreThorium, oreTitanium, oreScrap, oreCoal, oreLead, ice,
    // Misc
    waterExtractor;

    public static void load(){
        // Turrets
        duo = new ItemTurret("duo"){{
            requirements(Category.turret, with(Items.copper, 35));
            ammo(
                Items.copper,  new BasicBulletType(2.5f, 9){{
                    width = 7f;
                    height = 9f;
                    lifetime = 60f;
                    ammoMultiplier = 2;
                }}
            );

            shoot = new ShootAlternate(3.5f);

            recoils = 2;
            drawer = new DrawTurret(){{
                for(int i = 0; i < 2; i ++){
                    int f = i;
                    parts.add(new RegionPart("-barrel-" + (i == 0 ? "l" : "r")){{
                        progress = PartProgress.recoil;
                        recoilIndex = f;
                        under = true;
                        moveY = -1.5f;
                    }});
                }
            }};

            recoil = 0.5f;
            shootY = 3f;
            reload = 20f;
            range = 110;
            shootCone = 15f;
            ammoUseEffect = Fx.casing1;
            health = 250;
            inaccuracy = 4f;
            rotateSpeed = 10f;
            coolant = null;
            researchCostMultiplier = 0.07f;

            limitRange();
        }};

        scatter = new ItemTurret("scatter"){{
            requirements(Category.turret, with(Items.copper, 85, Items.lead, 45));
            ammo(
                Items.lead, new FlakBulletType(4.2f, 3){{
                    lifetime = 60f;
                    ammoMultiplier = 2f;
                    shootEffect = Fx.shootSmall;
                    width = 5f;
                    height = 7f;
                    hitEffect = Fx.flakExplosion;
                    splashDamage = 27f * 1.5f;
                    splashDamageRadius = 13f;
                }}
            );

            drawer = new DrawTurret(){{
                parts.add(new RegionPart("-mid"){{
                    progress = PartProgress.recoil;
                    under = false;
                    moveY = -1.25f;
                }});
            }};

            reload = 18f;
            range = 220f;
            size = 2;
            targetGround = false;

            shoot.shotDelay = 5f;
            shoot.shots = 2;

            recoil = 1f;
            rotateSpeed = 15f;
            inaccuracy = 27f;
            shootCone = 35f;

            scaledHealth = 200;
            shootSound = Sounds.shootSnap;
            coolant = null;
            researchCostMultiplier = 0.07f;

            limitRange(2);
        }};

        scorch = new ItemTurret("scorch"){{
            requirements(Category.turret, with(Items.copper, 25, Items.graphite, 22));
            ammo(
                Items.coal, new BulletType(3.35f, 17f){{
                    ammoMultiplier = 2f;
                    hitSize = 7f;
                    lifetime = 18f;
                    pierce = true;
                    collidesAir = false;
                    statusDuration = 60f * 3;
                    shootEffect = Fx.shootSmallFlame;
                    hitEffect = Fx.hitFlameSmall;
                    despawnEffect = Fx.none;
                    status = StatusEffects.burning;
                    keepVelocity = false;
                    hittable = false;
                }}
            );
            recoil = 0f;
            reload = 7f;
            coolantMultiplier = 1.5f;
            range = 60f;
            inaccuracy = 1f;
            shootY = 3;
            shootCone = 50f;
            targetAir = false;
            ammoUseEffect = Fx.none;
            health = 400;
            shootSound = Sounds.flame;
            coolant = null;
        }};

        hail = new ItemTurret("hail"){{
            requirements(Category.turret, with(Items.copper, 40, Items.graphite, 17));
            ammo(
                Items.graphite, new ArtilleryBulletType(3f, 20){{
                    knockback = 0.9f;
                    lifetime = 80f;
                    width = height = 11f;
                    collidesTiles = false;
                    splashDamageRadius = 20f * 0.75f;
                    splashDamage = 30f;
                }}
            );
            targetAir = false;
            reload = 60f;
            recoil = 2f;
            range = 230f;
            inaccuracy = 2f;
            shootCone = 10f;
            health = 260;
            shootSound = Sounds.bang;
            coolant = null;
            limitRange(0f);
        }};

        wave = new LiquidTurret("wave"){{
            requirements(Category.turret, with(Items.metaglass, 45, Items.lead, 75, Items.copper, 25));
            ammo(
                Liquids.water,new LiquidBulletType(Liquids.water){{
                    knockback = 0.8f;
                    drag = 0.01f;
                    layer = Layer.bullet - 2f;
                }}
            );
            size = 2;
            recoil = 0f;
            reload = 6f;
            inaccuracy = 10f;
            shootCone = 50f;
            liquidCapacity = 10f;
            shootEffect = Fx.shootLiquid;
            range = 110f;
            scaledHealth = 250;
            flags = EnumSet.of(BlockFlag.turret, BlockFlag.extinguisher);
        }};

        lancer = new PowerTurret("lancer"){{
            requirements(Category.turret, with(Items.copper, 60, Items.lead, 70, Items.silicon, 60, Items.titanium, 30));
            range = 165f;
//Can you make a comment in the middle of code? Turns out you can
            shoot.firstShotDelay = 40f;

            recoil = 2f;
            reload = 80f;
            shake = 2f;
            shootEffect = Fx.lancerLaserShoot;
            smokeEffect = Fx.none;
            heatColor = Color.red;
            size = 2;
            scaledHealth = 280;
            targetAir = false;
            moveWhileCharging = false;
            accurateDelay = false;
            shootSound = Sounds.laser;
            coolant = null;

            consumePower(12f);

            shootType = new LaserBulletType(140){{
                colors = new Color[]{Pal.lancerLaser.cpy().a(0.4f), Pal.lancerLaser, Color.white};

                chargeEffect = new MultiEffect(Fx.lancerLaserCharge, Fx.lancerLaserChargeBegin);
                buildingDamageMultiplier = 0.25f;
                hitEffect = Fx.hitLancer;
                hitSize = 4;
                lifetime = 16f;
                drawSize = 400f;
                collidesAir = false;
                length = 173f;
                ammoMultiplier = 1f;
                pierceCap = 4;
            }};
        }};

        arc = new PowerTurret("arc"){{
            requirements(Category.turret, with(Items.copper, 50, Items.lead, 50));
            shootType = new LightningBulletType(){{
                damage = 20;
                lightningLength = 25;
                collidesAir = false;
                ammoMultiplier = 1f;

                //for visual stats only.
                buildingDamageMultiplier = 0.25f;

                lightningType = new BulletType(0.0001f, 0f){{
                    lifetime = Fx.lightning.lifetime;
                    hitEffect = Fx.hitLancer;
                    despawnEffect = Fx.none;
                    status = StatusEffects.shocked;
                    statusDuration = 10f;
                    hittable = false;
                    lightColor = Color.white;
                    collidesAir = false;
                    buildingDamageMultiplier = 0.25f;
                }};
            }};
            reload = 35f;
            shootCone = 40f;
            rotateSpeed = 8f;
            targetAir = false;
            range = 90f;
            shootEffect = Fx.lightningShoot;
            heatColor = Color.red;
            recoil = 1f;
            size = 1;
            inaccuracy = 1f;
            health = 260;
            shootSound = Sounds.spark;
            consumePower(6.6666f);
            coolant = null;
        }};

        parallax = new TractorBeamTurret("parallax"){{
            requirements(Category.turret, with(Items.silicon, 120, Items.titanium, 90, Items.graphite, 30));

            hasPower = true;
            size = 2;
            force = 12f;
            scaledForce = 6f;
            range = 120f;
            damage = 0.3f;
            scaledHealth = 160;
            rotateSpeed = 10;

            consumePower(6f);
        }};

        swarmer = new ItemTurret("swarmer"){{
            requirements(Category.turret, with(Items.graphite, 35, Items.titanium, 35, Items.plastanium, 45, Items.silicon, 30));
            ammo(
                Items.blastCompound, new MissileBulletType(3.7f, 10){{
                    width = 8f;
                    height = 8f;
                    shrinkY = 0f;
                    splashDamageRadius = 30f;
                    splashDamage = 30f * 1.5f;
                    ammoMultiplier = 5f;
                    hitEffect = Fx.blastExplosion;
                    despawnEffect = Fx.blastExplosion;

                    status = StatusEffects.blasted;
                    statusDuration = 60f;
                }},
                Items.surgeAlloy, new MissileBulletType(3.7f, 18){{
                    width = 8f;
                    height = 8f;
                    shrinkY = 0f;
                    splashDamageRadius = 25f;
                    splashDamage = 25f * 1.4f;
                    hitEffect = Fx.blastExplosion;
                    despawnEffect = Fx.blastExplosion;
                    ammoMultiplier = 4f;
                    lightningDamage = 10;
                    lightning = 2;
                    lightningLength = 10;
                }}
            );
            
            shoot = new ShootBarrel(){{
                barrels = new float[]{
                    -4, -1.25f, 0,
                    0, 0, 0,
                    4, -1.25f, 0
                };
                shots = 4;
                shotDelay = 5f;
            }};

            shootY = 4.5f;
            reload = 30f;
            inaccuracy = 15f;
            range = 240f;
            consumeAmmoOnce = false;
            size = 2;
            scaledHealth = 300;
            shootSound = Sounds.missile;
            envEnabled |= Env.space;

            limitRange(5f);
            coolant = null;
        }};

        salvo = new ItemTurret("salvo"){{
            requirements(Category.turret, with(Items.copper, 100, Items.graphite, 80, Items.titanium, 50));
            ammo(
                Items.graphite, new BasicBulletType(3.5f, 20){{
                    width = 9f;
                    height = 12f;
                    reloadMultiplier = 0.6f;
                    ammoMultiplier = 4;
                    lifetime = 60f;
                }},
                Items.pyratite, new BasicBulletType(3.2f, 18){{
                    width = 10f;
                    height = 12f;
                    frontColor = Pal.lightishOrange;
                    backColor = Pal.lightOrange;
                    status = StatusEffects.burning;
                    hitEffect = new MultiEffect(Fx.hitBulletSmall, Fx.fireHit);

                    ammoMultiplier = 5;

                    splashDamage = 12f;
                    splashDamageRadius = 22f;

                    makeFire = true;
                    lifetime = 60f;
                }},

            drawer = new DrawTurret(){{
                parts.add(new RegionPart("-side"){{
                    progress = PartProgress.warmup;
                    moveX = 0.6f;
                    moveRot = -15f;
                    mirror = true;
                    layerOffset = 0.001f;
                    moves.add(new PartMove(PartProgress.recoil, 0.5f, -0.5f, -8f));
                }}, new RegionPart("-barrel"){{
                    progress = PartProgress.recoil;
                    moveY = -2.5f;
                }});
            }});

            size = 2;
            range = 190f;
            reload = 31f;
            consumeAmmoOnce = false;
            targetGround = false;
            ammoEjectBack = 3f;
            recoil = 0f;
            shake = 1f;
            shoot.shots = 4;
            shoot.shotDelay = 3f;

            ammoUseEffect = Fx.casing2;
            scaledHealth = 240;
            shootSound = Sounds.shootBig;

            limitRange();
            coolant = consumeCoolant(0.2f);
        }};

        segment = new PointDefenseTurret("segment"){{
            requirements(Category.turret, with(Items.silicon, 130, Items.thorium, 80, Items.phaseFabric, 40, Items.titanium, 40));

            scaledHealth = 250;
            range = 180f;
            hasPower = true;
            consumePower(16f);
            size = 2;
            shootLength = 5f;
            bulletDamage = 30f;
            reload = 8f;
            envEnabled |= Env.space;
        }};

        tsunami = new LiquidTurret("tsunami"){{
            requirements(Category.turret, with(Items.metaglass, 100, Items.lead, 400, Items.titanium, 250, Items.thorium, 100));
            ammo(
                Liquids.water, new LiquidBulletType(Liquids.water){{
                    lifetime = 49f;
                    speed = 4f;
                    knockback = 1.7f;
                    puddleSize = 8f;
                    orbSize = 4f;
                    drag = 0.001f;
                    ammoMultiplier = 0.4f;
                    statusDuration = 60f * 4f;
                    damage = 0.2f;
                    layer = Layer.bullet - 2f;
                }},
                Liquids.cryofluid, new LiquidBulletType(Liquids.cryofluid){{
                    lifetime = 49f;
                    speed = 4f;
                    knockback = 1.3f;
                    puddleSize = 8f;
                    orbSize = 4f;
                    drag = 0.001f;
                    ammoMultiplier = 0.4f;
                    statusDuration = 60f * 4f;
                    damage = 0.2f;
                }}
            );
            size = 3;
            reload = 3f;
            shoot.shots = 2;
            velocityRnd = 0.1f;
            inaccuracy = 4f;
            consumePower(4f);
            recoil = 1f;
            shootCone = 45f;
            liquidCapacity = 40f;
            shootEffect = Fx.shootLiquid;
            range = 190f;
            scaledHealth = 250;
            flags = EnumSet.of(BlockFlag.turret, BlockFlag.extinguisher);
        }};

        fuse = new ItemTurret("fuse"){{
            requirements(Category.turret, with(Items.copper, 225, Items.graphite, 225, Items.thorium, 100));

            reload = 35f;
            shake = 4f;
            range = 90f;
            recoil = 5f;

            shoot = new ShootSpread(3, 20f);

            shootCone = 30;
            size = 3;
            envEnabled |= Env.space;

            scaledHealth = 220;
            shootSound = Sounds.shotgun;
            coolant = consumeCoolant(0.3f);

            float brange = range + 10f;

            ammo(
                Items.titanium, new ShrapnelBulletType(){{
                    length = brange;
                    damage = 66f;
                    ammoMultiplier = 2f;
                    width = 17f;
                    reloadMultiplier = 1.3f;
                }});
        }};

        ripple = new ItemTurret("ripple"){{
            requirements(Category.turret, with(Items.copper, 150, Items.graphite, 135, Items.titanium, 60));
            ammo(
                Items.silicon, new ArtilleryBulletType(3f, 20){{
                    knockback = 0.8f;
                    lifetime = 80f;
                    width = height = 11f;
                    collidesTiles = false;
                    splashDamageRadius = 25f * 0.75f;
                    splashDamage = 33f;
                    reloadMultiplier = 1.2f;
                    ammoMultiplier = 3f;
                    homingPower = 0.08f;
                    homingRange = 50f;
                }},
                Items.plastanium, new ArtilleryBulletType(3.4f, 20, "shell"){{
                    hitEffect = Fx.plasticExplosion;
                    knockback = 1f;
                    lifetime = 80f;
                    width = height = 13f;
                    collidesTiles = false;
                    splashDamageRadius = 35f * 0.75f;
                    splashDamage = 45f;
                    fragBullet = new BasicBulletType(2.5f, 10, "bullet"){{
                        width = 10f;
                        height = 12f;
                        shrinkY = 1f;
                        lifetime = 15f;
                        backColor = Pal.plastaniumBack;
                        frontColor = Pal.plastaniumFront;
                        despawnEffect = Fx.none;
                        collidesAir = false;
                    }};
                    fragBullets = 10;
                    backColor = Pal.plastaniumBack;
                    frontColor = Pal.plastaniumFront;
                }}
            );

            targetAir = false;
            size = 3;
            shoot.shots = 4;
            inaccuracy = 24f;
            reload = 60f;
            ammoEjectBack = 5f;
            ammoUseEffect = Fx.casing3Double;
            ammoPerShot = 2;
            velocityRnd = 0.2f;
            recoil = 6f;
            shake = 200f;
            range = 290f;
            minRange = 50f;
            coolant = consumeCoolant(0.3f);

            scaledHealth = 130;
            shootSound = Sounds.artillery;
        }};

        cyclone = new ItemTurret("cyclone"){{
            requirements(Category.turret, with(Items.copper, 200, Items.titanium, 125, Items.plastanium, 80));
            ammo(
                Items.metaglass, new FlakBulletType(4f, 6){{
                    ammoMultiplier = 2f;
                    shootEffect = Fx.shootSmall;
                    reloadMultiplier = 0.8f;
                    width = 6f;
                    height = 8f;
                    hitEffect = Fx.flakExplosion;
                    splashDamage = 45f;
                    splashDamageRadius = 25f;
                    fragBullet = new BasicBulletType(3f, 12, "bullet"){{
                        width = 5f;
                        height = 12f;
                        shrinkY = 1f;
                        lifetime = 20f;
                        backColor = Pal.gray;
                        frontColor = Color.white;
                        despawnEffect = Fx.none;
                    }};
                    fragBullets = 4;
                    explodeRange = 20f;
                    collidesGround = true;
                }},
                Items.surgeAlloy, new FlakBulletType(4.5f, 13){{
                    ammoMultiplier = 5f;
                    splashDamage = 50f * 1.5f;
                    splashDamageRadius = 38f;
                    lightning = 2;
                    lightningLength = 7;
                    shootEffect = Fx.shootBig;
                    collidesGround = true;
                    explodeRange = 20f;
                }}
            );
            shootY = 10f;

            shoot = new ShootBarrel(){{
                barrels = new float[]{
                0f, 1f, 0f,
                3f, 0f, 0f,
                -3f, 0f, 0f,
                };
            }};

            recoils = 3;
            drawer = new DrawTurret(){{
                for(int i = 3; i > 0; i--){
                    int f = i;
                    parts.add(new RegionPart("-barrel-" + i){{
                        progress = PartProgress.recoil;
                        recoilIndex = f - 1;
                        under = true;
                        moveY = -2f;
                    }});
                }
            }};

            reload = 16f;
            range = 200f;
            size = 3;
            recoil = 1.5f;
            recoilTime = 10;
            rotateSpeed = 10f;
            inaccuracy = 10f;
            shootCone = 30f;
            shootSound = Sounds.shootSnap;
            coolant = null;

            scaledHealth = 145;
            limitRange();
        }};

        foreshadow = new ItemTurret("foreshadow"){{
            float brange = range = 250f;

            requirements(Category.turret, with(Items.copper, 1000, Items.metaglass, 600, Items.surgeAlloy, 300, Items.plastanium, 200, Items.silicon, 600));
            ammo(
                Items.surgeAlloy, new RailBulletType(){{
                    shootEffect = Fx.instShoot;
                    hitEffect = Fx.instHit;
                    pierceEffect = Fx.railHit;
                    smokeEffect = Fx.smokeCloud;
                    pointEffect = Fx.instTrail;
                    despawnEffect = Fx.instBomb;
                    pointEffectSpace = 20f;
                    damage = 1350;
                    buildingDamageMultiplier = 0.2f;
                    pierceDamageFactor = 1f;
                    length = brange;
                    hitShake = 6f;
                    ammoMultiplier = 1f;
                }}
            );

            maxAmmo = 40;
            ammoPerShot = 10;
            rotateSpeed = 2f;
            reload = 400f;
            ammoUseEffect = Fx.casing3Double;
            recoil = 5f;
            cooldownTime = reload;
            shake = 4f;
            size = 4;
            shootCone = 2f;
            shootSound = Sounds.railgun;
            unitSort = UnitSorts.strongest;
            envEnabled |= Env.space;

            coolantMultiplier = 0.4f;
            scaledHealth = 150;

            coolant = consumeCoolant(1f);
            consumePower(10f);
        }};

        spectre = new ItemTurret("spectre"){{
            requirements(Category.turret, with(Items.copper, 900, Items.graphite, 300, Items.surgeAlloy, 250, Items.plastanium, 175, Items.thorium, 250));
            ammo(
                Items.thorium, new BasicBulletType(8f, 80){{
                    hitSize = 5;
                    width = 16f;
                    height = 23f;
                    shootEffect = Fx.shootBig;
                    pierceCap = 2;
                    pierceBuilding = true;
                    knockback = 0.7f;
                }},
                Items.pyratite, new BasicBulletType(7f, 70){{
                    hitSize = 5;
                    width = 16f;
                    height = 21f;
                    frontColor = Pal.lightishOrange;
                    backColor = Pal.lightOrange;
                    status = StatusEffects.burning;
                    hitEffect = new MultiEffect(Fx.hitBulletSmall, Fx.fireHit);
                    shootEffect = Fx.shootBig;
                    makeFire = true;
                    pierceCap = 2;
                    pierceBuilding = true;
                    knockback = 0.6f;
                    ammoMultiplier = 3;
                    splashDamage = 20f;
                    splashDamageRadius = 25f;
                }}
            );
            reload = 14f;
            recoilTime = reload * 2f;
            coolantMultiplier = 0.5f;
            ammoUseEffect = Fx.casing3;
            range = 260f;
            inaccuracy = 3f;
            recoil = 3f;
            shoot = new ShootAlternate(8f);
            shake = 2f;
            size = 4;
            shootCone = 24f;
            shootSound = Sounds.shootBig;

            scaledHealth = 160;
            coolant = null;//cowseatgrass

            limitRange();
        }};

        meltdown = new LaserTurret("meltdown"){{
            requirements(Category.turret, with(Items.copper, 1200, Items.lead, 350, Items.graphite, 300, Items.surgeAlloy, 325, Items.silicon, 325));
            shootEffect = Fx.shootBigSmoke2;
            shootCone = 40f;
            recoil = 4f;
            size = 4;
            shake = 2f;
            range = 195f;
            reload = 90f;
            firingMoveFract = 0.5f;
            shootDuration = 230f;
            shootSound = Sounds.laserbig;
            loopSound = Sounds.beam;
            loopSoundVolume = 2f;
            envEnabled |= Env.space;

            shootType = new ContinuousLaserBulletType(78){{
                length = 200f;
                hitEffect = Fx.hitMeltdown;
                hitColor = Pal.meltdownHit;
                status = StatusEffects.melting;
                drawSize = 420f;

                incendChance = 0.4f;
                incendSpread = 5f;
                incendAmount = 1;
                ammoMultiplier = 1f;
            }};

            scaledHealth = 200;
            coolant = consumeCoolant(10f);
            consumePower(34f);
        }};

        // DRILL

        mechanicalDrill = new Drill("mechanical-drill"){{
            requirements(Category.production, with(Items.copper, 12));
            tier = 2;
            drillTime = 600;
            size = 2;
            //mechanical drill doesn't work in space
            envEnabled ^= Env.space;
            researchCost = with(Items.copper, 10);

            consumeLiquid(Liquids.water, 0.05f).boost();
        }};

        pneumaticDrill = new Drill("pneumatic-drill"){{
            requirements(Category.production, with(Items.copper, 18, Items.graphite, 10));
            tier = 3;
            drillTime = 400;
            size = 2;

            consumeLiquid(Liquids.water, 0.06f).boost();
        }};

        laserDrill = new Drill("laser-drill"){{
            requirements(Category.production, with(Items.copper, 35, Items.graphite, 30, Items.silicon, 30, Items.titanium, 20));
            drillTime = 280;
            size = 3;
            hasPower = true;
            tier = 4;
            updateEffect = Fx.pulverizeMedium;
            drillEffect = Fx.mineBig;

            consumePower(1.10f);
            consumeLiquid(Liquids.water, 0.08f).boost();
        }};

        blastDrill = new Drill("blast-drill"){{
            requirements(Category.production, with(Items.copper, 65, Items.silicon, 60, Items.titanium, 50, Items.thorium, 75));
            drillTime = 280;
            size = 4;
            drawRim = true;
            hasPower = true;
            tier = 5;
            updateEffect = Fx.pulverizeRed;
            updateEffectChance = 0.03f;
            drillEffect = Fx.mineHuge;
            rotateSpeed = 6f;
            warmupSpeed = 0.01f;
            itemCapacity = 20;

            //more than the laser drill
            liquidBoostIntensity = 1.8f;

            consumePower(3f);
            consumeLiquid(Liquids.water, 0.1f).boost();
        }};

        mechanicalPump = new Drill("mechanical-pump"){{
            requirements(Category.liquid, with(Items.copper, 15, Items.metaglass, 10));
            tier = 0;
        }};

        waterExtractor = new Drill("water-extractor"){{
            requirements(Category.production, with(Items.metaglass, 30, Items.graphite, 30, Items.lead, 30, Items.copper, 30));
            size = 2;
            rotateSpeed = 1.4f;
            tier = 0;

            consumePower(1.7f);
        }};

        // ORES

        oreCopper = new LiquidOre(IsenLiquids.copper){{
            oreDefault = true;
            oreThreshold = 0.81f;
            oreScale = 23.47619f;
        }};

        oreLead = new LiquidOre(IsenLiquids.lead){{
            oreDefault = true;
            oreThreshold = 0.828f;
            oreScale = 23.952381f;
        }};

        oreScrap = new LiquidOre(Liquids.slag);

        oreCoal = new LiquidOre(IsenLiquids.coal){{
            oreDefault = true;
            oreThreshold = 0.846f;
            oreScale = 24.428572f;
        }};

        oreTitanium = new LiquidOre(IsenLiquids.titanium){{
            oreDefault = true;
            oreThreshold = 0.864f;
            oreScale = 24.904762f;
        }};

        oreThorium = new LiquidOre(IsenLiquids.thorium){{
            oreDefault = true;
            oreThreshold = 0.882f;
            oreScale = 25.380953f;
        }};

        ice = new OreBlock(IsenItems.ice){{
            oreDefault = true;
            oreThreshold = 0.7f;
            oreScale = 40f;
        }};

        /* This whole section below is only for hiding existing blocks, do not add something
         * that doesnt have said function
         */

         waterExtractor = new SolidPump("water-extractor"){{
            category = null;
        }};

        mechanicalPump = new Pump("mechanical-pump"){{
            category = null;
        }};

    }
}
