// Package Declaration
package me.iffa.bspace.gui;

// bSpace Imports
import me.iffa.bspace.api.SpaceMessageHandler;
import me.iffa.bspace.config.SpaceConfig;
import me.iffa.bspace.config.SpaceConfig.ConfigFile;
import me.iffa.bspace.config.SpaceConfig.Defaults;
import me.iffa.bspace.api.SpaceLangHandler;

// Bukkit Imports
import org.bukkit.configuration.file.YamlConfiguration;

// Java Imports
import java.awt.Desktop;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import java.io.IOException;
import java.util.logging.Level;

/**
 * Interface for Pail, a Bukkit GUI.
 * 
 * @author iffa
 * @author Jack
 */
public class PailInterface extends javax.swing.JPanel {
    // Variables
    public static YamlConfiguration spaceConfig = SpaceConfig.getConfig(ConfigFile.CONFIG);
    public static YamlConfiguration idConfig = SpaceConfig.getConfig(ConfigFile.IDS);
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for PailInterface.
     */
    public PailInterface() {
        initComponents();
        readConfigs();
    }

    /**
     * Reads the configuration files and changes the interface values to represent the configuration values.
     */
    private void readConfigs() {
        CheckBoxHelmet.setSelected(spaceConfig.getBoolean("global.givehelmet", (Boolean) Defaults.HELMET_GIVEN.getDefault()));
        CheckBoxSuit.setSelected(spaceConfig.getBoolean("global.givesuit", (Boolean) Defaults.SUIT_GIVEN.getDefault()));
        ArmorTypeBox.setText(spaceConfig.getString("global.armortype", (String) Defaults.ARMOR_TYPE.getDefault()));
        HelmetBlockIdBox.setText(spaceConfig.getString("global.blockid", String.valueOf((Integer) Defaults.HELMET_BLOCK.getDefault())));
        SpaceList.setModel(new DefaultListModel());
        if (idConfig.getConfigurationSection("ids") != null) {
            for (String id : idConfig.getConfigurationSection("ids").getKeys(false)) {
                ((DefaultListModel) SpaceList.getModel()).addElement(id);
                SpaceMessageHandler.debugPrint(Level.INFO, "Added ID '" + id + "' to list of IDs (Pail).");
            }
        }
    }

    /**
     * Loads the configuration settings for the ID selected in the list. 
     * Only one safety check is made (to make sure an ID that doesn't exist is not loaded).
     * 
     * @param idname ID name
     */
    private void loadSpaceListConfig(String idname) {
        if (idConfig.get("ids." + idname) == null) {
            SpaceMessageHandler.print(Level.WARNING, SpaceLangHandler.getIdNotFoundMessage(idname));
            return;
        }
        Settings_IDName.setText(idname);
        Settings_Planets.setSelected(idConfig.getBoolean("ids." + idname + ".generation.generateplanets", (Boolean) Defaults.GENERATE_PLANETS.getDefault()));
        Settings_Asteroids.setSelected(idConfig.getBoolean("ids." + idname + "generation.generateasteroids", (Boolean) Defaults.ASTEROIDS_ENABLED.getDefault()));
        Settings_GlowstoneChance.setValue(idConfig.getInt("ids." + idname + ".generation.glowstonechance", (Integer) Defaults.GLOWSTONE_CHANCE.getDefault()));
        Settings_StoneChance.setValue(idConfig.getInt("ids." + idname + ".generation.stonechance", (Integer) Defaults.STONE_CHANCE.getDefault()));
        Settings_Night.setSelected(idConfig.getBoolean("ids." + idname + ".alwaysnight", (Boolean) Defaults.FORCE_NIGHT.getDefault()));
        Settings_HelmetRequired.setSelected(idConfig.getBoolean("ids." + idname + ".helmet.required", (Boolean) Defaults.REQUIRE_HELMET.getDefault()));
        Settings_SuitRequired.setSelected(idConfig.getBoolean("ids." + idname + ".suit.required", (Boolean) Defaults.REQUIRE_SUIT.getDefault()));
        Settings_Weather.setSelected(idConfig.getBoolean("ids." + idname + ".weather", (Boolean) Defaults.ALLOW_WEATHER.getDefault()));
        Settings_Nether.setSelected(idConfig.getBoolean("ids." + idname + ".nethermode", false));
        Settings_RoomHeight.setValue(idConfig.getInt("ids." + idname + ".breathingarea.maxroomheight", (Integer) Defaults.ROOM_HEIGHT.getDefault()));
        Settings_Neutral.setSelected(idConfig.getBoolean("ids." + idname + ".neutralmobs", (Boolean) Defaults.NEUTRAL_MOBS_ALLOWED.getDefault()));
        Settings_Hostile.setSelected(idConfig.getBoolean("ids." + idname + ".hostilemobs", (Boolean) Defaults.HOSTILE_MOBS_ALLOWED.getDefault()));
        SpaceMessageHandler.debugPrint(Level.INFO, "Loaded settings for id '" + idname + "'.");
    }

    /**
     * Saves the configuration settings for the ID selected in the list. 
     * Only one safety check is made (to make sure a ID that doesn't exist is not saved).
     * 
     * @param idname ID name
     */
    private void saveIdConfig(String idname) {
        if (idConfig.get("ids." + idname) == null) {
            SpaceMessageHandler.print(Level.WARNING, SpaceLangHandler.getIdNotFoundMessage(idname));
            return;
        }
        idConfig.set("ids." + idname + ".generation.generateplanets", Settings_Planets.isSelected());
        idConfig.set("ids." + idname + ".generation.generateasteroids", Settings_Asteroids.isSelected());
        idConfig.set("ids." + idname + ".generation.glowstonechance", (Integer) Settings_GlowstoneChance.getValue());
        idConfig.set("ids." + idname + ".generation.stonechance", (Integer) Settings_StoneChance.getValue());
        idConfig.set("ids." + idname + ".weather", Settings_Weather.isSelected());
        idConfig.set("ids." + idname + ".hostilemobs", Settings_Hostile.isSelected());
        idConfig.set("ids." + idname + ".neutralmobs", Settings_Neutral.isSelected());
        idConfig.set("ids." + idname + ".alwaysnight", Settings_Night.isSelected());
        idConfig.set("ids." + idname + ".nethermode", Settings_Nether.isSelected());
        idConfig.set("ids." + idname + ".suit.required", Settings_SuitRequired.isSelected());
        idConfig.set("ids." + idname + ".helmet.required", Settings_HelmetRequired.isSelected());
        try {
            idConfig.save(SpaceConfig.getConfigFile(ConfigFile.IDS));
        } catch (IOException ex) {
            SpaceMessageHandler.print(Level.WARNING, ex.getMessage());
        }
        SpaceMessageHandler.debugPrint(Level.INFO, "Saved settings for id '" + idname + "'.");
    }

    /**
     * Adds a ID to the list in the GUI.
     * 
     * @param idname ID name
     */
    public void addSpaceList(String idname) {
        ((DefaultListModel) SpaceList.getModel()).addElement(idname);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GlobalSettings = new javax.swing.JPanel();
        CheckBoxHelmet = new javax.swing.JCheckBox();
        CheckBoxSuit = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        HelmetBlockIdBox = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ArmorTypeBox = new javax.swing.JTextField();
        SpoutEnabled = new javax.swing.JCheckBox();
        ResetButton = new javax.swing.JButton();
        SaveButton = new javax.swing.JButton();
        ids = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        SpaceList = new javax.swing.JList();
        createIdButton = new javax.swing.JButton();
        deleteIdButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        newID = new javax.swing.JTextField();
        Settings = new javax.swing.JPanel();
        Settings_Save = new javax.swing.JButton();
        Settings_Reset = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        Settings_IDName = new javax.swing.JLabel();
        Settings_Planets = new javax.swing.JCheckBox();
        Settings_Asteroids = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        Settings_StoneChance = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        Settings_GlowstoneChance = new javax.swing.JSpinner();
        Settings_HelmetRequired = new javax.swing.JCheckBox();
        Settings_SuitRequired = new javax.swing.JCheckBox();
        Settings_Weather = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        Settings_Nether = new javax.swing.JCheckBox();
        Settings_Hostile = new javax.swing.JCheckBox();
        Settings_Neutral = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        Settings_RoomHeight = new javax.swing.JSpinner();
        Settings_Night = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();

        setFont(new java.awt.Font("Arial", 0, 11));
        setInheritsPopupMenu(true);
        setMaximumSize(new java.awt.Dimension(850, 450));
        setMinimumSize(new java.awt.Dimension(850, 450));
        setPreferredSize(new java.awt.Dimension(850, 450));
        setRequestFocusEnabled(false);

        GlobalSettings.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Global Settings", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        GlobalSettings.setToolTipText("Global settings. These affect each and every spaceworld.");

        CheckBoxHelmet.setText("Give helmet");
        CheckBoxHelmet.setToolTipText("Selected if a spacehelmet should be given when going to a space world.");
        CheckBoxHelmet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBoxHelmetActionPerformed(evt);
            }
        });

        CheckBoxSuit.setText("Give suit");
        CheckBoxSuit.setToolTipText("Selected if a spacehelmet should be given when going to a space world.");
        CheckBoxSuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBoxSuitActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel5.setText("Helmet block id:");
        jLabel5.setToolTipText("The block id that will be the helmet.");

        HelmetBlockIdBox.setText("86");
        HelmetBlockIdBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HelmetBlockIdBoxActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel4.setText("Suit armortype:");

        ArmorTypeBox.setText("iron");
        ArmorTypeBox.setToolTipText("The spacesuit armortype. Can be diamond, chainmail, gold, iron or leather.");
        ArmorTypeBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ArmorTypeBoxActionPerformed(evt);
            }
        });

        SpoutEnabled.setSelected(true);
        SpoutEnabled.setText("Use Spout features");
        SpoutEnabled.setToolTipText("Checked if you want to enable Spout features.");
        SpoutEnabled.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SpoutEnabledActionPerformed(evt);
            }
        });

        ResetButton.setText("Revert");
        ResetButton.setToolTipText("Not happy with the changes? Just press this button and all your changes will be reset!");
        ResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetButtonActionPerformed(evt);
            }
        });

        SaveButton.setText("Save");
        SaveButton.setToolTipText("Saves the changes and reloads the server for changes to take effect.");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout GlobalSettingsLayout = new javax.swing.GroupLayout(GlobalSettings);
        GlobalSettings.setLayout(GlobalSettingsLayout);
        GlobalSettingsLayout.setHorizontalGroup(
            GlobalSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GlobalSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(GlobalSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(GlobalSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(GlobalSettingsLayout.createSequentialGroup()
                            .addComponent(CheckBoxHelmet, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(HelmetBlockIdBox, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(24, 24, 24))
                        .addGroup(GlobalSettingsLayout.createSequentialGroup()
                            .addComponent(CheckBoxSuit)
                            .addGap(12, 12, 12)
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(ArmorTypeBox, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                    .addGroup(GlobalSettingsLayout.createSequentialGroup()
                        .addComponent(SpoutEnabled)
                        .addGap(115, 115, 115)))
                .addGroup(GlobalSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ResetButton)
                    .addGroup(GlobalSettingsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SaveButton, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)))
                .addContainerGap())
        );
        GlobalSettingsLayout.setVerticalGroup(
            GlobalSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GlobalSettingsLayout.createSequentialGroup()
                .addGroup(GlobalSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CheckBoxHelmet, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(HelmetBlockIdBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ResetButton))
                .addGroup(GlobalSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(GlobalSettingsLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(GlobalSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CheckBoxSuit, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(ArmorTypeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SpoutEnabled, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(GlobalSettingsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SaveButton)
                        .addContainerGap())))
        );

        ids.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "IDs", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        ids.setToolTipText("Panel with buttons to create and delete IDs.");
        ids.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        SpaceList.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        SpaceList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "No user IDs" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        SpaceList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        SpaceList.setToolTipText("All IDs. Selecting an ID on the list opens its settings on the right.");
        SpaceList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                SpaceListValueChanged(evt);
            }
        });
        SpaceList.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                SpaceListFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                SpaceListFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(SpaceList);

        createIdButton.setFont(new java.awt.Font("Arial", 0, 11));
        createIdButton.setText("Create");
        createIdButton.setToolTipText("Creates a new ID with the name on the box above.");
        createIdButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createIdButtonActionPerformed(evt);
            }
        });

        deleteIdButton.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        deleteIdButton.setText("Delete");
        deleteIdButton.setToolTipText("Select an ID from the list and click this button to delete the ID.");
        deleteIdButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteIdButtonActionPerformed(evt);
            }
        });

        newID.setText("ID");
        newID.setToolTipText("Specify a name for the ID. Must not be an ID already, and must not be empty or contain spaces.");

        javax.swing.GroupLayout idsLayout = new javax.swing.GroupLayout(ids);
        ids.setLayout(idsLayout);
        idsLayout.setHorizontalGroup(
            idsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(idsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(idsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(idsLayout.createSequentialGroup()
                        .addGroup(idsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(newID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                            .addComponent(createIdButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(60, 60, 60)
                        .addComponent(jLabel1))
                    .addComponent(deleteIdButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        idsLayout.setVerticalGroup(
            idsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(idsLayout.createSequentialGroup()
                .addGroup(idsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(idsLayout.createSequentialGroup()
                        .addComponent(newID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(idsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(idsLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(147, 147, 147))
                            .addGroup(idsLayout.createSequentialGroup()
                                .addComponent(createIdButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteIdButton)))))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        Settings.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ID Settings", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        Settings.setToolTipText("Settings for the currently selected ID in the list.");
        Settings.setMaximumSize(new java.awt.Dimension(200, 32767));
        Settings.setPreferredSize(new java.awt.Dimension(200, 250));

        Settings_Save.setText("Save");
        Settings_Save.setToolTipText("Saves the changes made to the ID's settings.");
        Settings_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Settings_SaveActionPerformed(evt);
            }
        });

        Settings_Reset.setText("Revert");
        Settings_Reset.setToolTipText("Resets the changes made to the ID's settings.");
        Settings_Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Settings_ResetActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel2.setText("Currently editing:");

        Settings_IDName.setText("None");

        Settings_Planets.setSelected(true);
        Settings_Planets.setText("Generate planets");
        Settings_Planets.setToolTipText("Checked if planets are generated.");
        Settings_Planets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Settings_PlanetsActionPerformed(evt);
            }
        });

        Settings_Asteroids.setSelected(true);
        Settings_Asteroids.setText("Generate asteroids");
        Settings_Asteroids.setToolTipText("Asteroids are small patches of glowstone and stone. The frequency can be changed.");

        jLabel3.setText("Stone-asteroid chance:");

        Settings_StoneChance.setModel(new javax.swing.SpinnerNumberModel(3, 1, 200, 1));
        Settings_StoneChance.setToolTipText("The stone-asteroid spawning chance. From 1 to 200.");

        jLabel6.setText("Glowstone-asteroid chance:");

        Settings_GlowstoneChance.setModel(new javax.swing.SpinnerNumberModel(1, 1, 200, 1));
        Settings_GlowstoneChance.setToolTipText("The glowstone-asteroid spawning chance. From 1 to 200.");

        Settings_HelmetRequired.setText("Helmet required");
        Settings_HelmetRequired.setToolTipText("Checked if helmets are required in a spaceworld to survive.");

        Settings_SuitRequired.setText("Suit required");
        Settings_SuitRequired.setToolTipText("Checked if suits are required in a spaceworld to survive.");

        Settings_Weather.setText("Weather");
        Settings_Weather.setToolTipText("Selected if weather is allowed in the spaceworlds.");

        jSeparator1.setForeground(new java.awt.Color(210, 213, 215));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        Settings_Nether.setText("Nethermode");
        Settings_Nether.setToolTipText("Checked if the spaceworld should be like nether. (red fog and nether mobs)");

        Settings_Hostile.setText("Hostile mobs");
        Settings_Hostile.setToolTipText("Selected if hostile mobs are allowed to spawn.");
        Settings_Hostile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Settings_HostileActionPerformed(evt);
            }
        });

        Settings_Neutral.setSelected(true);
        Settings_Neutral.setText("Neutral mobs");
        Settings_Neutral.setToolTipText("Selected if neutral mobs are allowed to spawn.");

        jLabel7.setText("Maximum room height:");

        Settings_RoomHeight.setModel(new javax.swing.SpinnerNumberModel(5, 1, 64, 1));
        Settings_RoomHeight.setToolTipText("The maximum height of a room where you can breathe in. In a zone like this, no helmets or suits are required.");

        Settings_Night.setSelected(true);
        Settings_Night.setText("Always night");
        Settings_Night.setToolTipText("Checked if it should always be night in the spaceworld.");
        Settings_Night.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Settings_NightActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SettingsLayout = new javax.swing.GroupLayout(Settings);
        Settings.setLayout(SettingsLayout);
        SettingsLayout.setHorizontalGroup(
            SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SettingsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SettingsLayout.createSequentialGroup()
                        .addComponent(Settings_Save)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Settings_Reset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Settings_IDName, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SettingsLayout.createSequentialGroup()
                        .addGroup(SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Settings_SuitRequired)
                            .addComponent(Settings_HelmetRequired)
                            .addComponent(Settings_Planets)
                            .addGroup(SettingsLayout.createSequentialGroup()
                                .addGroup(SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Settings_Asteroids)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Settings_StoneChance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(SettingsLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Settings_GlowstoneChance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Settings_Night)
                            .addComponent(Settings_Weather)
                            .addComponent(Settings_Nether)
                            .addComponent(Settings_Neutral)
                            .addComponent(Settings_Hostile)
                            .addGroup(SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(Settings_RoomHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)))))
                .addGap(72, 72, 72))
        );
        SettingsLayout.setVerticalGroup(
            SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SettingsLayout.createSequentialGroup()
                .addGroup(SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Settings_Save)
                    .addComponent(Settings_Reset)
                    .addComponent(jLabel2)
                    .addComponent(Settings_IDName))
                .addGap(26, 26, 26)
                .addGroup(SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(SettingsLayout.createSequentialGroup()
                            .addComponent(Settings_Planets, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(Settings_Asteroids, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(Settings_StoneChance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(SettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(Settings_GlowstoneChance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(Settings_HelmetRequired, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(Settings_SuitRequired, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(SettingsLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)))
                    .addGroup(SettingsLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(Settings_Weather, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(Settings_Night, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Settings_Nether, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(Settings_Neutral, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(Settings_Hostile, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Settings_RoomHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)))
                .addContainerGap())
        );

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bananaspace.png"))); // NOI18N
        jLabel8.setText("  ");
        jLabel8.setOpaque(true);
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ids, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(Settings, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(GlobalSettings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(399, 399, 399))
            .addGroup(layout.createSequentialGroup()
                .addGap(234, 234, 234)
                .addComponent(jLabel8)
                .addContainerGap(447, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(GlobalSettings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ids, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Settings, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)))
        );

        ids.getAccessibleContext().setAccessibleName("Ids");
    }// </editor-fold>//GEN-END:initComponents

    private void CheckBoxHelmetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckBoxHelmetActionPerformed
    }//GEN-LAST:event_CheckBoxHelmetActionPerformed

    private void CheckBoxSuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckBoxSuitActionPerformed
    }//GEN-LAST:event_CheckBoxSuitActionPerformed

    private void ArmorTypeBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ArmorTypeBoxActionPerformed
    }//GEN-LAST:event_ArmorTypeBoxActionPerformed

    private void HelmetBlockIdBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HelmetBlockIdBoxActionPerformed
    }//GEN-LAST:event_HelmetBlockIdBoxActionPerformed

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed

        spaceConfig.set("global.givesuit", CheckBoxSuit.isSelected());
        spaceConfig.set("global.givehelmet", CheckBoxHelmet.isSelected());
        spaceConfig.set("global.armortype", ArmorTypeBox.getText());
        spaceConfig.set("global.blockid", Integer.parseInt(HelmetBlockIdBox.getText()));
        spaceConfig.set("global.usespout", SpoutEnabled.isSelected());
        try {
            spaceConfig.save(SpaceConfig.getConfigFile(ConfigFile.CONFIG));
        } catch (IOException ex) {
            SpaceMessageHandler.print(Level.WARNING, ex.getMessage());
        }
        JOptionPane.showMessageDialog(this, "Your general settings have been saved!", "Settings saved!", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_SaveButtonActionPerformed

    private void ResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetButtonActionPerformed
        JOptionPane.showMessageDialog(this, "Your changes have been reset!", "Changes reset!", JOptionPane.INFORMATION_MESSAGE);
        readConfigs();
    }//GEN-LAST:event_ResetButtonActionPerformed

private void createIdButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createIdButtonActionPerformed
    String idname = newID.getText().trim();
    if (idname.equalsIgnoreCase("")) {
        JOptionPane.showMessageDialog(this, "The ID cannot be empty!", "Invalid ID", JOptionPane.WARNING_MESSAGE);
        return;
    }
    if (idConfig.getConfigurationSection("ids") != null && idConfig.getConfigurationSection("ids").contains(idname)) {
        JOptionPane.showMessageDialog(this, "An ID with the given name already exists!", "Invalid ID", JOptionPane.WARNING_MESSAGE);
        return;
    }
    if (idname.contains(" ")) {
        JOptionPane.showMessageDialog(this, "The ID cannot contain spaces! Replace spaces with underscores.", "Invalid ID", JOptionPane.WARNING_MESSAGE);
        return;
    }
    idConfig.set("ids." + idname + ".generation.generateplanets", Defaults.GENERATE_PLANETS.getDefault());
    idConfig.set("ids." + idname + ".generation.generateasteroids", Defaults.ASTEROIDS_ENABLED.getDefault());
    idConfig.set("ids." + idname + ".generation.glowstonechance", Defaults.GLOWSTONE_CHANCE.getDefault());
    idConfig.set("ids." + idname + ".generation.stonechance", Defaults.STONE_CHANCE.getDefault());
    idConfig.set("ids." + idname + ".weather", Defaults.ALLOW_WEATHER.getDefault());
    idConfig.set("ids." + idname + ".hostilemobs", Defaults.HOSTILE_MOBS_ALLOWED.getDefault());
    idConfig.set("ids." + idname + ".neutralmobs", Defaults.NEUTRAL_MOBS_ALLOWED.getDefault());
    idConfig.set("ids." + idname + ".alwaysnight", Defaults.FORCE_NIGHT);
    idConfig.set("ids." + idname + ".nethermode", false);
    idConfig.set("ids." + idname + ".suit.required", Defaults.REQUIRE_SUIT.getDefault());
    idConfig.set("ids." + idname + ".helmet.required", Defaults.REQUIRE_HELMET.getDefault());
    try {
        idConfig.save(SpaceConfig.getConfigFile(ConfigFile.IDS));
    } catch (IOException ex) {
        SpaceMessageHandler.print(Level.WARNING, ex.getMessage());
    }
    ((DefaultListModel) SpaceList.getModel()).addElement(idname);
    SpaceMessageHandler.debugPrint(Level.INFO, "Created ID '" + idname + "' through Pail.");
    JOptionPane.showMessageDialog(this, "A new ID called '" + idname + "' has been created!", "ID created", JOptionPane.INFORMATION_MESSAGE);
    newID.setText("ID");
}//GEN-LAST:event_createIdButtonActionPerformed

private void deleteIdButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteIdButtonActionPerformed

    if (SpaceList.getSelectedIndex() == -1) {
        JOptionPane.showMessageDialog(this, "You need to choose an ID to delete from the list!", "Select an ID", JOptionPane.WARNING_MESSAGE);
        return;
    }
    String s = (String) SpaceList.getModel().getElementAt(SpaceList.getSelectedIndex());
    int n = JOptionPane.showConfirmDialog(
            this,
            "Are you sure?",
            "Delete an ID",
            JOptionPane.YES_NO_OPTION);
    if (n == JOptionPane.YES_OPTION) {
        idConfig.set("ids." + s, null);
        try {
            idConfig.save(SpaceConfig.getConfigFile(ConfigFile.IDS));
        } catch (IOException ex) {
            SpaceMessageHandler.print(Level.WARNING, ex.getMessage());
        }
        ((DefaultListModel) SpaceList.getModel()).remove(SpaceList.getSelectedIndex());
        Settings_IDName.setText("None");
        JOptionPane.showMessageDialog(this, "The ID was deleted successfully!", "ID deleted", JOptionPane.INFORMATION_MESSAGE);
    }
}//GEN-LAST:event_deleteIdButtonActionPerformed

private void Settings_ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Settings_ResetActionPerformed
    if (SpaceList.getSelectedIndex() != -1) {
        String idname = (String) SpaceList.getModel().getElementAt(SpaceList.getSelectedIndex());
        loadSpaceListConfig(idname);
        JOptionPane.showMessageDialog(this, "Your changes have been reset!", "Changes reset!", JOptionPane.INFORMATION_MESSAGE);
    }
}//GEN-LAST:event_Settings_ResetActionPerformed

private void Settings_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Settings_SaveActionPerformed
    if (SpaceList.getSelectedIndex() != -1) {
        String idname = (String) SpaceList.getModel().getElementAt(SpaceList.getSelectedIndex());
        saveIdConfig(idname);
        JOptionPane.showMessageDialog(this, "The ID '" + idname + "' has been saved. Please note that most changes take effect after reloading the server.", "ID saved!", JOptionPane.INFORMATION_MESSAGE);
    }
}//GEN-LAST:event_Settings_SaveActionPerformed

private void Settings_PlanetsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Settings_PlanetsActionPerformed
}//GEN-LAST:event_Settings_PlanetsActionPerformed

private void SpoutEnabledActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SpoutEnabledActionPerformed
}//GEN-LAST:event_SpoutEnabledActionPerformed

private void SpaceListFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SpaceListFocusLost
}//GEN-LAST:event_SpaceListFocusLost

private void SpaceListFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SpaceListFocusGained
}//GEN-LAST:event_SpaceListFocusGained

private void SpaceListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_SpaceListValueChanged
    if (SpaceList.getSelectedIndex() != -1) {
        String idname = (String) SpaceList.getModel().getElementAt(SpaceList.getSelectedIndex());
        loadSpaceListConfig(idname);
    }
}//GEN-LAST:event_SpaceListValueChanged

private void Settings_HostileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Settings_HostileActionPerformed
}//GEN-LAST:event_Settings_HostileActionPerformed

private void Settings_NightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Settings_NightActionPerformed
}//GEN-LAST:event_Settings_NightActionPerformed

private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
    try {
        Desktop.getDesktop().browse(java.net.URI.create("http://forums.bukkit.org/threads/32546/"));
    } catch (IOException ex) {
        SpaceMessageHandler.print(Level.WARNING, "Something went wrong while opening a page on your web browser!");
    }
}//GEN-LAST:event_jLabel8MouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ArmorTypeBox;
    private javax.swing.JCheckBox CheckBoxHelmet;
    private javax.swing.JCheckBox CheckBoxSuit;
    private javax.swing.JPanel GlobalSettings;
    private javax.swing.JTextField HelmetBlockIdBox;
    private javax.swing.JButton ResetButton;
    private javax.swing.JButton SaveButton;
    private javax.swing.JPanel Settings;
    private javax.swing.JCheckBox Settings_Asteroids;
    private javax.swing.JSpinner Settings_GlowstoneChance;
    private javax.swing.JCheckBox Settings_HelmetRequired;
    private javax.swing.JCheckBox Settings_Hostile;
    private javax.swing.JLabel Settings_IDName;
    private javax.swing.JCheckBox Settings_Nether;
    private javax.swing.JCheckBox Settings_Neutral;
    private javax.swing.JCheckBox Settings_Night;
    private javax.swing.JCheckBox Settings_Planets;
    private javax.swing.JButton Settings_Reset;
    private javax.swing.JSpinner Settings_RoomHeight;
    private javax.swing.JButton Settings_Save;
    private javax.swing.JSpinner Settings_StoneChance;
    private javax.swing.JCheckBox Settings_SuitRequired;
    private javax.swing.JCheckBox Settings_Weather;
    private javax.swing.JList SpaceList;
    private javax.swing.JCheckBox SpoutEnabled;
    private javax.swing.JButton createIdButton;
    private javax.swing.JButton deleteIdButton;
    private javax.swing.JPanel ids;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField newID;
    // End of variables declaration//GEN-END:variables
}