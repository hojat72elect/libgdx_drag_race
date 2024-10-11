package com.nopalsoft.dragracer.shop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.nopalsoft.dragracer.Assets;
import com.nopalsoft.dragracer.MainStreet;
import com.nopalsoft.dragracer.Settings;

public class CharactersSubMenu {

    // cars
    public static final int SKIN_CAR_DEVIL = 0;
    public static final int SKIN_CAR_BANSHEE = 1;
    public static final int SKIN_CAR_TURISM = 3;
    public static final int SKIN_CAR_BULLET = 6;
    public static final int SKIN_CAR_TORNADO = 2;
    public static final int SKIN_CAR_AUDI_S5 = 4;
    public static final int SKIN_CAR_BMW_X6 = 5;
    public static final int SKIN_CAR_CHEVROLET_CROSSFIRE = 7;
    public static final int SKIN_CAR_CITROEN_C4 = 8;
    public static final int SKIN_CAR_DODGE_CHARGER = 9;
    public static final int SKIN_CAR_FIAT_500_LOUNGE = 10;
    public static final int SKIN_CAR_HONDA_CRV = 11;
    public static final int SKIN_CAR_MAZDA_6 = 12;
    public static final int SKIN_CAR_MAZDA_RX8 = 13;
    public static final int SKIN_CAR_SEAT_IBIZA = 14;
    public static final int SKIN_CAR_VOLKSWAGEN_SCIROCCO = 15;

    private final static Preferences pref = Gdx.app.getPreferences("com.tiar.dragrace.shop");

    // prices
    final int PRICE_BANSHEE = 50;
    final int PRICE_BULLET = 175;
    final int PRICE_TURISM = 100;
    final int PRICE_TORNADO = 75;
    final int PRICE_CAR_AUDI_S5 = 125;
    final int PRICE_CAR_BMW_X6 = 150;
    final int PRICE_CAR_CHEVROLET_CROSSFIRE = 200;
    final int PRICE_CAR_CITROEN_C4 = 225;
    final int PRICE_CAR_DODGE_CHARGER = 250;
    final int PRICE_CAR_FIAT_500_LOUNGE = 275;
    final int PRICE_CAR_HONDA_CRV = 300;
    final int PRICE_CAR_MAZDA_6 = 325;
    final int PRICE_CAR_MAZDA_RX8 = 350;
    final int PRICE_CAR_SEAT_IBIZA = 375;
    final int PRICE_CAR_VOLKSWAGEN_SCIROCCO = 400;

    boolean didBuyBanshee, didBuyTornado, didBuyTurismo, didBuyAudiS5,
            didBuyBmwX6, didBuyBullet, didBuyCrossfire, didBuyCitroenC4,
            didBuyDodgeCharger, didBuyFiat500, didBuyHondaCRV, didBuyMazda6,
            didBuyMazdaRX8, didBuySeatIbiza, didBuyVolkswagenScirocco;
    TextButton buttonBuyDiablo, buttonBuyBanshee, buttonBuyTornado, buttonBuyTurismo,
            buttonBuyAudiS5, buttonBuyBmwX6, buttonBuyBullet, buttonBuyCrossfire,
            buttonBuyCitroenC4, buttonBuyDodgeCharger, buttonBuyFiat500Lounge,
            buttonBuyHondaCRV, buttonBuyMazda6, buttonBuyMazdaRX8, buttonBuySeatIbiza,
            buttonBuyVolkswagenScirocco;
    Array<TextButton> arrayButtons;
    Table container;
    MainStreet game;

    public CharactersSubMenu(MainStreet game, Table container) {
        this.game = game;
        this.container = container;
        loadPurchases();

        container.clear();

        Label labelPriceBanshee = null;
        Label labelPriceTornado = null;
        Label labelPriceTurismo = null;
        Label labelPriceAudiS5 = null;
        Label labelPriceBmwX6 = null;
        Label labelPriceCamaro = null;
        Label labelPriceCrossfire = null;
        Label labelPriceCitroenC4 = null;
        Label labelPriceDodgeCharger = null;
        Label labelPriceFiat500Lounge = null;
        Label labelPriceHondeCRV = null;
        Label labelPriceMazda6 = null;
        Label labelPriceMazdaRX8 = null;
        Label labelPriceSeatIbiza = null;
        Label labelPriceVolkswagenScirocco = null;

        if (!didBuyBanshee)
            labelPriceBanshee = new Label(PRICE_BANSHEE + "", Assets.labelStyleSmall);
        if (!didBuyTornado)
            labelPriceTornado = new Label(PRICE_TORNADO + "", Assets.labelStyleSmall);

        if (!didBuyTurismo)
            labelPriceTurismo = new Label(PRICE_TURISM + "", Assets.labelStyleSmall);

        if (!didBuyAudiS5)
            labelPriceAudiS5 = new Label(PRICE_CAR_AUDI_S5 + "", Assets.labelStyleSmall);

        if (!didBuyBmwX6)
            labelPriceBmwX6 = new Label(PRICE_CAR_BMW_X6 + "", Assets.labelStyleSmall);

        if (!didBuyBullet)
            labelPriceCamaro = new Label(PRICE_BULLET + "", Assets.labelStyleSmall);

        if (!didBuyCrossfire)
            labelPriceCrossfire = new Label(PRICE_CAR_CHEVROLET_CROSSFIRE + "", Assets.labelStyleSmall);

        if (!didBuyCitroenC4)
            labelPriceCitroenC4 = new Label(PRICE_CAR_CITROEN_C4 + "", Assets.labelStyleSmall);

        if (!didBuyDodgeCharger)
            labelPriceDodgeCharger = new Label(PRICE_CAR_DODGE_CHARGER + "", Assets.labelStyleSmall);

        if (!didBuyFiat500)
            labelPriceFiat500Lounge = new Label(PRICE_CAR_FIAT_500_LOUNGE + "", Assets.labelStyleSmall);

        if (!didBuyHondaCRV)
            labelPriceHondeCRV = new Label(PRICE_CAR_HONDA_CRV + "", Assets.labelStyleSmall);

        if (!didBuyMazda6)
            labelPriceMazda6 = new Label(PRICE_CAR_MAZDA_6 + "", Assets.labelStyleSmall);

        if (!didBuyMazdaRX8)
            labelPriceMazdaRX8 = new Label(PRICE_CAR_MAZDA_RX8 + "", Assets.labelStyleSmall);

        if (!didBuySeatIbiza)
            labelPriceSeatIbiza = new Label(PRICE_CAR_SEAT_IBIZA + "", Assets.labelStyleSmall);

        if (!didBuyVolkswagenScirocco)
            labelPriceVolkswagenScirocco = new Label(PRICE_CAR_VOLKSWAGEN_SCIROCCO + "", Assets.labelStyleSmall);

        initializeButtons();

        container.add(new Image(Assets.horizontalSeparator)).expandX().fill()
                .height(5);
        container.row();

        // User Default
        container
                .add(agregarPersonajeTabla(
                        "Diablo",
                        null,
                        Assets.carDevil,
                        "Good car. It's not the fastest, but it's got great handling although maybe a little too twitchy for some.",
                        buttonBuyDiablo)).expandX().fill();
        container.row();

        // SKIN_CARRO_BANSHEE
        container
                .add(agregarPersonajeTabla(
                        "Banshee",
                        labelPriceBanshee,
                        Assets.carBanshee,
                        "Looks great and drives even better. Awesome acceleration and slight oversteer make this a thrilling ride.",
                        buttonBuyBanshee)).expandX().fill();
        container.row();

        // SKIN_CARRO_TORNADO
        container
                .add(agregarPersonajeTabla(
                        "Tornado",
                        labelPriceTornado,
                        Assets.carTornado,
                        "Pretty speedy. Nothing too hot about this car, it looks ok and is ok to drive.",
                        buttonBuyTornado)).expandX().fill();
        container.row();

        // SKIN_CARRO_TURISMO
        container
                .add(agregarPersonajeTabla(
                        "Turismo",
                        labelPriceTurismo,
                        Assets.carTourism,
                        "If you can get this rare sport car, you'll be rewarded with a superbly fast drive. If you get it, take care of it.",
                        buttonBuyTurismo)).expandX().fill();
        container.row();

        // SKIN_CARRO_AUDI_S5
        container
                .add(agregarPersonajeTabla("Ventura", labelPriceAudiS5,
                        Assets.carAudiS5, "No description", buttonBuyAudiS5))
                .expandX().fill();
        container.row();

        // SKIN_CARRO_BMW_X6
        container
                .add(agregarPersonajeTabla("XMW", labelPriceBmwX6, Assets.carBmwX6,
                        "No description", buttonBuyBmwX6)).expandX().fill();
        container.row();

        // PRECIO_BULLET
        container
                .add(agregarPersonajeTabla(
                        "Bullet",
                        labelPriceCamaro,
                        Assets.carBullet,
                        "Probably the best sporty hatchback. It's quick and sticks to road really well. Acceleration is great too.",
                        buttonBuyBullet)).expandX().fill();
        container.row();

        // SKIN_CARRO_CHEVRLOTE_CROSSFIRE
        container
                .add(agregarPersonajeTabla("Crosstown", labelPriceCrossfire,
                        Assets.carChevroletCrossfire, "No description",
                        buttonBuyCrossfire)).expandX().fill();
        container.row();

        // SKIN_CARRO_CITROEN_C4
        container
                .add(agregarPersonajeTabla("Omega X", labelPriceCitroenC4,
                        Assets.carCitroenC4, "No description", buttonBuyCitroenC4))
                .expandX().fill();
        container.row();

        // SKIN_CARRO_DODGE_CHARGER
        container
                .add(agregarPersonajeTabla("Vulcano", labelPriceDodgeCharger,
                        Assets.carDodgeCharger, "No description",
                        buttonBuyDodgeCharger)).expandX().fill();
        container.row();

        // SKIN_CARRO_FIAT_500_LOUNGE
        container
                .add(agregarPersonajeTabla("Fiesta", labelPriceFiat500Lounge,
                        Assets.carFiat500Lounge, "No description",
                        buttonBuyFiat500Lounge)).expandX().fill();
        container.row();

        // SKIN_CARRO_HONDA_CRV
        container
                .add(agregarPersonajeTabla("Comander", labelPriceHondeCRV,
                        Assets.carHondaCRV, "No description", buttonBuyHondaCRV))
                .expandX().fill();
        container.row();

        // SKIN_CARRO_MAZDA_6
        container
                .add(agregarPersonajeTabla("Orion", labelPriceMazda6,
                        Assets.carMazda6, "No description", buttonBuyMazda6))
                .expandX().fill();
        container.row();

        // SKIN_CARRO_MAZDA_RX8
        container
                .add(agregarPersonajeTabla("Colorado", labelPriceMazdaRX8,
                        Assets.carMazdaRX8, "No description", buttonBuyMazdaRX8))
                .expandX().fill();
        container.row();

        // SKIN_CARRO_SEAT_IBIZA
        container
                .add(agregarPersonajeTabla("Formosa", labelPriceSeatIbiza,
                        Assets.carSeatIbiza, "No description", buttonBuySeatIbiza))
                .expandX().fill();
        container.row();

        // SKIN_CARRO_VOLKSWAGEN_SCIROCCO
        container
                .add(agregarPersonajeTabla("SHU", labelPriceVolkswagenScirocco,
                        Assets.carVolkswagenScirocco, "No description",
                        buttonBuyVolkswagenScirocco)).expandX().fill();
        container.row();

    }

    private Table agregarPersonajeTabla(String titulo, Label lblPrecio,
                                        AtlasRegion imagen, String descripcion, TextButton boton) {

        Image moneda = new Image(Assets.coinFront);
        Image imgPersonaje = new Image(imagen);

        if (lblPrecio == null)
            moneda.setVisible(false);

        Table tbBarraTitulo = new Table();
        tbBarraTitulo.add(new Label(titulo, Assets.labelStyleSmall)).expandX()
                .left();
        tbBarraTitulo.add(moneda).right();
        tbBarraTitulo.add(lblPrecio).right().padRight(10);

        Table tbContent = new Table();
        // tbContent.debug();
        tbContent.add(tbBarraTitulo).expandX().fill().colspan(2).padTop(8);
        tbContent.row();
        tbContent.add(imgPersonaje).left().pad(10).size(40, 90);

        Label lblDescripcion = new Label(descripcion, Assets.labelStyleSmall);
        lblDescripcion.setWrap(true);
        lblDescripcion.setFontScale(.85f);
        tbContent.add(lblDescripcion).expand().fill().padLeft(5);

        tbContent.row().colspan(2);
        tbContent.add(boton).expandX().right().padRight(10).size(120, 45);
        tbContent.row().colspan(2);
        tbContent.add(new Image(Assets.horizontalSeparator)).expandX().fill()
                .height(5).padTop(15);

        return tbContent;

    }

    private void initializeButtons() {
        arrayButtons = new Array<>();

        // DEFAULT
        buttonBuyDiablo = new TextButton("Select", Assets.styleTextButtonPurchased);
        if (Settings.selectedSkin == SKIN_CAR_DEVIL)
            buttonBuyDiablo.setVisible(false);

        addPressEffect(buttonBuyDiablo);
        buttonBuyDiablo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Settings.selectedSkin = SKIN_CAR_DEVIL;
                setSelected(buttonBuyDiablo);
            }
        });

        // SKIN_CAR_BANSHEE
        if (didBuyBanshee)
            buttonBuyBanshee = new TextButton("Select",
                    Assets.styleTextButtonPurchased);
        else
            buttonBuyBanshee = new TextButton("Buy", Assets.styleTextButtonBuy);

        if (Settings.selectedSkin == SKIN_CAR_BANSHEE)
            buttonBuyBanshee.setVisible(false);

        addPressEffect(buttonBuyBanshee);
        buttonBuyBanshee.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (didBuyBanshee) {
                    Settings.selectedSkin = SKIN_CAR_BANSHEE;
                    setSelected(buttonBuyBanshee);
                } else if (Settings.coinsTotal >= PRICE_BANSHEE) {
                    Settings.coinsTotal -= PRICE_BANSHEE;
                    setButtonStylePurchased(buttonBuyBanshee);
                    didBuyBanshee = true;
                }
                savePurchases();
            }
        });

        // SKIN_CAR_TORNADO
        if (didBuyTornado)
            buttonBuyTornado = new TextButton("Select",
                    Assets.styleTextButtonPurchased);
        else
            buttonBuyTornado = new TextButton("Buy", Assets.styleTextButtonBuy);

        if (Settings.selectedSkin == SKIN_CAR_TORNADO)
            buttonBuyTornado.setVisible(false);

        addPressEffect(buttonBuyTornado);
        buttonBuyTornado.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (didBuyTornado) {
                    Settings.selectedSkin = SKIN_CAR_TORNADO;
                    setSelected(buttonBuyTornado);
                } else if (Settings.coinsTotal >= PRICE_TORNADO) {
                    Settings.coinsTotal -= PRICE_TORNADO;
                    setButtonStylePurchased(buttonBuyTornado);
                    didBuyTornado = true;
                }
                savePurchases();
            }
        });

        // SKIN_CAR_TURISMO
        if (didBuyTurismo)
            buttonBuyTurismo = new TextButton("Select",
                    Assets.styleTextButtonPurchased);
        else
            buttonBuyTurismo = new TextButton("Buy", Assets.styleTextButtonBuy);

        if (Settings.selectedSkin == SKIN_CAR_TURISM)
            buttonBuyTurismo.setVisible(false);

        addPressEffect(buttonBuyTurismo);
        buttonBuyTurismo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (didBuyTurismo) {
                    Settings.selectedSkin = SKIN_CAR_TURISM;
                    setSelected(buttonBuyTurismo);
                } else if (Settings.coinsTotal >= PRICE_TURISM) {
                    Settings.coinsTotal -= PRICE_TURISM;
                    setButtonStylePurchased(buttonBuyTurismo);
                    didBuyTurismo = true;
                }
                savePurchases();
            }
        });

        // SKIN_CAR_AUDI_S5
        if (didBuyAudiS5)
            buttonBuyAudiS5 = new TextButton("Select",
                    Assets.styleTextButtonPurchased);
        else
            buttonBuyAudiS5 = new TextButton("Buy", Assets.styleTextButtonBuy);

        if (Settings.selectedSkin == SKIN_CAR_AUDI_S5)
            buttonBuyAudiS5.setVisible(false);

        addPressEffect(buttonBuyAudiS5);
        buttonBuyAudiS5.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (didBuyAudiS5) {
                    Settings.selectedSkin = SKIN_CAR_AUDI_S5;
                    setSelected(buttonBuyAudiS5);
                } else if (Settings.coinsTotal >= PRICE_CAR_AUDI_S5) {
                    Settings.coinsTotal -= PRICE_CAR_AUDI_S5;
                    setButtonStylePurchased(buttonBuyAudiS5);
                    didBuyAudiS5 = true;
                }
                savePurchases();
            }
        });

        // SKIN_CAR_BMW_X6
        if (didBuyBmwX6)
            buttonBuyBmwX6 = new TextButton("Select",
                    Assets.styleTextButtonPurchased);
        else
            buttonBuyBmwX6 = new TextButton("Buy", Assets.styleTextButtonBuy);

        if (Settings.selectedSkin == SKIN_CAR_BMW_X6)
            buttonBuyBmwX6.setVisible(false);

        addPressEffect(buttonBuyBmwX6);
        buttonBuyBmwX6.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (didBuyBmwX6) {
                    Settings.selectedSkin = SKIN_CAR_BMW_X6;
                    setSelected(buttonBuyBmwX6);
                } else if (Settings.coinsTotal >= PRICE_CAR_BMW_X6) {
                    Settings.coinsTotal -= PRICE_CAR_BMW_X6;
                    setButtonStylePurchased(buttonBuyBmwX6);
                    didBuyBmwX6 = true;
                }
                savePurchases();
            }
        });

        // SKIN_CAR_BULLET
        if (didBuyBullet)
            buttonBuyBullet = new TextButton("Select",
                    Assets.styleTextButtonPurchased);
        else
            buttonBuyBullet = new TextButton("Buy", Assets.styleTextButtonBuy);

        if (Settings.selectedSkin == SKIN_CAR_BULLET)
            buttonBuyBullet.setVisible(false);

        addPressEffect(buttonBuyBullet);
        buttonBuyBullet.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (didBuyBullet) {
                    Settings.selectedSkin = SKIN_CAR_BULLET;
                    setSelected(buttonBuyBullet);
                } else if (Settings.coinsTotal >= PRICE_BULLET) {
                    Settings.coinsTotal -= PRICE_BULLET;
                    setButtonStylePurchased(buttonBuyBullet);
                    didBuyBullet = true;
                }
                savePurchases();
            }
        });

        // SKIN_CAR_CHEVROLET_CROSSFIRE
        if (didBuyCrossfire)
            buttonBuyCrossfire = new TextButton("Select",
                    Assets.styleTextButtonPurchased);
        else
            buttonBuyCrossfire = new TextButton("Buy", Assets.styleTextButtonBuy);

        if (Settings.selectedSkin == SKIN_CAR_CHEVROLET_CROSSFIRE)
            buttonBuyCrossfire.setVisible(false);

        addPressEffect(buttonBuyCrossfire);
        buttonBuyCrossfire.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (didBuyCrossfire) {
                    Settings.selectedSkin = SKIN_CAR_CHEVROLET_CROSSFIRE;
                    setSelected(buttonBuyCrossfire);
                } else if (Settings.coinsTotal >= PRICE_CAR_CHEVROLET_CROSSFIRE) {
                    Settings.coinsTotal -= PRICE_CAR_CHEVROLET_CROSSFIRE;
                    setButtonStylePurchased(buttonBuyCrossfire);
                    didBuyCrossfire = true;
                }
                savePurchases();
            }
        });

        // SKIN_CAR_CITROEN_C4
        if (didBuyCitroenC4)
            buttonBuyCitroenC4 = new TextButton("Select",
                    Assets.styleTextButtonPurchased);
        else
            buttonBuyCitroenC4 = new TextButton("Buy", Assets.styleTextButtonBuy);

        if (Settings.selectedSkin == SKIN_CAR_CITROEN_C4)
            buttonBuyCitroenC4.setVisible(false);

        addPressEffect(buttonBuyCitroenC4);
        buttonBuyCitroenC4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (didBuyCitroenC4) {
                    Settings.selectedSkin = SKIN_CAR_CITROEN_C4;
                    setSelected(buttonBuyCitroenC4);
                } else if (Settings.coinsTotal >= PRICE_CAR_CITROEN_C4) {
                    Settings.coinsTotal -= PRICE_CAR_CITROEN_C4;
                    setButtonStylePurchased(buttonBuyCitroenC4);
                    didBuyCitroenC4 = true;
                }
                savePurchases();
            }
        });

        // SKIN_CAR_DODGE_CHARGER
        if (didBuyDodgeCharger)
            buttonBuyDodgeCharger = new TextButton("Select",
                    Assets.styleTextButtonPurchased);
        else
            buttonBuyDodgeCharger = new TextButton("Buy", Assets.styleTextButtonBuy);

        if (Settings.selectedSkin == SKIN_CAR_DODGE_CHARGER)
            buttonBuyDodgeCharger.setVisible(false);

        addPressEffect(buttonBuyDodgeCharger);
        buttonBuyDodgeCharger.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (didBuyDodgeCharger) {
                    Settings.selectedSkin = SKIN_CAR_DODGE_CHARGER;
                    setSelected(buttonBuyDodgeCharger);
                } else if (Settings.coinsTotal >= PRICE_CAR_DODGE_CHARGER) {
                    Settings.coinsTotal -= PRICE_CAR_DODGE_CHARGER;
                    setButtonStylePurchased(buttonBuyDodgeCharger);
                    didBuyDodgeCharger = true;
                }
                savePurchases();
            }
        });

        // SKIN_CAR_FIAT_500_LOUNGE
        if (didBuyFiat500)
            buttonBuyFiat500Lounge = new TextButton("Select",
                    Assets.styleTextButtonPurchased);
        else
            buttonBuyFiat500Lounge = new TextButton("Buy",
                    Assets.styleTextButtonBuy);

        if (Settings.selectedSkin == SKIN_CAR_FIAT_500_LOUNGE)
            buttonBuyFiat500Lounge.setVisible(false);

        addPressEffect(buttonBuyFiat500Lounge);
        buttonBuyFiat500Lounge.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (didBuyFiat500) {
                    Settings.selectedSkin = SKIN_CAR_FIAT_500_LOUNGE;
                    setSelected(buttonBuyFiat500Lounge);
                } else if (Settings.coinsTotal >= PRICE_CAR_FIAT_500_LOUNGE) {
                    Settings.coinsTotal -= PRICE_CAR_FIAT_500_LOUNGE;
                    setButtonStylePurchased(buttonBuyFiat500Lounge);
                    didBuyFiat500 = true;
                }
                savePurchases();
            }
        });

        // SKIN_CAR_HONDA_CRV
        if (didBuyHondaCRV)
            buttonBuyHondaCRV = new TextButton("Select",
                    Assets.styleTextButtonPurchased);
        else
            buttonBuyHondaCRV = new TextButton("Buy", Assets.styleTextButtonBuy);

        if (Settings.selectedSkin == SKIN_CAR_HONDA_CRV)
            buttonBuyHondaCRV.setVisible(false);

        addPressEffect(buttonBuyHondaCRV);
        buttonBuyHondaCRV.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (didBuyHondaCRV) {
                    Settings.selectedSkin = SKIN_CAR_HONDA_CRV;
                    setSelected(buttonBuyHondaCRV);
                } else if (Settings.coinsTotal >= PRICE_CAR_HONDA_CRV) {
                    Settings.coinsTotal -= PRICE_CAR_HONDA_CRV;
                    setButtonStylePurchased(buttonBuyHondaCRV);
                    didBuyHondaCRV = true;
                }
                savePurchases();
            }
        });

        // SKIN_CAR_MAZDA_6
        if (didBuyMazda6)
            buttonBuyMazda6 = new TextButton("Select",
                    Assets.styleTextButtonPurchased);
        else
            buttonBuyMazda6 = new TextButton("Buy", Assets.styleTextButtonBuy);

        if (Settings.selectedSkin == SKIN_CAR_MAZDA_6)
            buttonBuyMazda6.setVisible(false);

        addPressEffect(buttonBuyMazda6);
        buttonBuyMazda6.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (didBuyMazda6) {
                    Settings.selectedSkin = SKIN_CAR_MAZDA_6;
                    setSelected(buttonBuyMazda6);
                } else if (Settings.coinsTotal >= PRICE_CAR_MAZDA_6) {
                    Settings.coinsTotal -= PRICE_CAR_MAZDA_6;
                    setButtonStylePurchased(buttonBuyMazda6);
                    didBuyMazda6 = true;
                }
                savePurchases();
            }
        });

        // SKIN_CAR_MAZDA_RX8
        if (didBuyMazdaRX8)
            buttonBuyMazdaRX8 = new TextButton("Select",
                    Assets.styleTextButtonPurchased);
        else
            buttonBuyMazdaRX8 = new TextButton("Buy", Assets.styleTextButtonBuy);

        if (Settings.selectedSkin == SKIN_CAR_MAZDA_RX8)
            buttonBuyMazdaRX8.setVisible(false);

        addPressEffect(buttonBuyMazdaRX8);
        buttonBuyMazdaRX8.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (didBuyMazdaRX8) {
                    Settings.selectedSkin = SKIN_CAR_MAZDA_RX8;
                    setSelected(buttonBuyMazdaRX8);
                } else if (Settings.coinsTotal >= PRICE_CAR_MAZDA_RX8) {
                    Settings.coinsTotal -= PRICE_CAR_MAZDA_RX8;
                    setButtonStylePurchased(buttonBuyMazdaRX8);
                    didBuyMazdaRX8 = true;
                }
                savePurchases();
            }
        });

        // SKIN_CAR_SEAT_IBIZA
        if (didBuySeatIbiza)
            buttonBuySeatIbiza = new TextButton("Select",
                    Assets.styleTextButtonPurchased);
        else
            buttonBuySeatIbiza = new TextButton("Buy", Assets.styleTextButtonBuy);

        if (Settings.selectedSkin == SKIN_CAR_SEAT_IBIZA)
            buttonBuySeatIbiza.setVisible(false);

        addPressEffect(buttonBuySeatIbiza);
        buttonBuySeatIbiza.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (didBuySeatIbiza) {
                    Settings.selectedSkin = SKIN_CAR_SEAT_IBIZA;
                    setSelected(buttonBuySeatIbiza);
                } else if (Settings.coinsTotal >= PRICE_CAR_SEAT_IBIZA) {
                    Settings.coinsTotal -= PRICE_CAR_SEAT_IBIZA;
                    setButtonStylePurchased(buttonBuySeatIbiza);
                    didBuySeatIbiza = true;
                }
                savePurchases();
            }
        });

        // SKIN_CAR_VOLKSWAGEN_SCIROCCO
        if (didBuyVolkswagenScirocco)
            buttonBuyVolkswagenScirocco = new TextButton("Select",
                    Assets.styleTextButtonPurchased);
        else
            buttonBuyVolkswagenScirocco = new TextButton("Buy",
                    Assets.styleTextButtonBuy);

        if (Settings.selectedSkin == SKIN_CAR_VOLKSWAGEN_SCIROCCO)
            buttonBuyVolkswagenScirocco.setVisible(false);

        addPressEffect(buttonBuyVolkswagenScirocco);
        buttonBuyVolkswagenScirocco.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (didBuyVolkswagenScirocco) {
                    Settings.selectedSkin = SKIN_CAR_VOLKSWAGEN_SCIROCCO;
                    setSelected(buttonBuyVolkswagenScirocco);
                } else if (Settings.coinsTotal >= PRICE_CAR_VOLKSWAGEN_SCIROCCO) {
                    Settings.coinsTotal -= PRICE_CAR_VOLKSWAGEN_SCIROCCO;
                    setButtonStylePurchased(buttonBuyVolkswagenScirocco);
                    didBuyVolkswagenScirocco = true;
                }
                savePurchases();
            }
        });

        arrayButtons.add(buttonBuyDiablo);
        arrayButtons.add(buttonBuyBanshee);
        arrayButtons.add(buttonBuyTornado);
        arrayButtons.add(buttonBuyTurismo);
        arrayButtons.add(buttonBuyAudiS5);
        arrayButtons.add(buttonBuyBmwX6);
        arrayButtons.add(buttonBuyBullet);
        arrayButtons.add(buttonBuyCrossfire);
        arrayButtons.add(buttonBuyCitroenC4);
        arrayButtons.add(buttonBuyDodgeCharger);
        arrayButtons.add(buttonBuyFiat500Lounge);
        arrayButtons.add(buttonBuyHondaCRV);
        arrayButtons.add(buttonBuyMazda6);
        arrayButtons.add(buttonBuyMazdaRX8);
        arrayButtons.add(buttonBuySeatIbiza);
        arrayButtons.add(buttonBuyVolkswagenScirocco);

    }

    private void loadPurchases() {
        didBuyBanshee = pref.getBoolean("didBuyBanshee", false);
        didBuyTornado = pref.getBoolean("didBuyTornado", false);
        didBuyTurismo = pref.getBoolean("didBuyTurismo", false);
        didBuyAudiS5 = pref.getBoolean("didBuyAudiS5", false);
        didBuyBmwX6 = pref.getBoolean("didBuyBmwX6", false);
        didBuyBullet = pref.getBoolean("didBuyBullet", false);
        didBuyCrossfire = pref.getBoolean("didBuyCrossfire", false);
        didBuyCitroenC4 = pref.getBoolean("didBuyCitroenC4", false);
        didBuyDodgeCharger = pref.getBoolean("didBuyDodgeCharger", false);
        didBuyFiat500 = pref.getBoolean("didBuyFiat500", false);
        didBuyHondaCRV = pref.getBoolean("didBuyHondaCRV", false);
        didBuyMazda6 = pref.getBoolean("didBuyMazda6", false);
        didBuyMazdaRX8 = pref.getBoolean("didBuyMazdaRX8", false);
        didBuySeatIbiza = pref.getBoolean("didBuySeatIbiza", false);
        didBuyVolkswagenScirocco = pref.getBoolean("didBuyVolkswagenScirocco", false);
    }

    private void savePurchases() {
        pref.putBoolean("didBuyBanshee", didBuyBanshee);
        pref.putBoolean("didBuyTornado", didBuyTornado);
        pref.putBoolean("didBuyTurismo", didBuyTurismo);
        pref.putBoolean("didBuyAudiS5", didBuyAudiS5);
        pref.putBoolean("didBuyBmwX6", didBuyBmwX6);
        pref.putBoolean("didBuyBullet", didBuyBullet);
        pref.putBoolean("didBuyCrossfire", didBuyCrossfire);
        pref.putBoolean("didBuyCitroenC4", didBuyCitroenC4);
        pref.putBoolean("didBuyDodgeCharger", didBuyDodgeCharger);
        pref.putBoolean("didBuyFiat500", didBuyFiat500);
        pref.putBoolean("didBuyHondaCRV", didBuyHondaCRV);
        pref.putBoolean("didBuyMazda6", didBuyMazda6);
        pref.putBoolean("didBuyMazdaRX8", didBuyMazdaRX8);
        pref.putBoolean("didBuySeatIbiza", didBuySeatIbiza);
        pref.putBoolean("didBuyVolkswagenScirocco", didBuyVolkswagenScirocco);
        pref.flush();
        Settings.save();
    }

    private void setButtonStylePurchased(TextButton button) {
        button.setStyle(Assets.styleTextButtonPurchased);
        button.setText("Select");
    }

    private void setSelected(TextButton button) {
        // All buttons are visible in the beginning  and at the end the selected button will be invisible.
        for (TextButton arrayButton : arrayButtons) {
            arrayButton.setVisible(true);
        }
        button.setVisible(false);
    }

    protected void addPressEffect(final Actor actor) {
        actor.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                actor.setPosition(actor.getX(), actor.getY() - 3);
                event.stop();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y,
                                int pointer, int button) {
                actor.setPosition(actor.getX(), actor.getY() + 3);
            }
        });
    }
}
