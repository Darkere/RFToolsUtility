package mcjty.rftoolsutility.modules.screen;

import mcjty.lib.container.GenericContainer;
import mcjty.rftoolsutility.RFToolsUtility;
import mcjty.rftoolsutility.modules.screen.blocks.*;
import mcjty.rftoolsutility.modules.screen.items.*;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static mcjty.rftoolsutility.RFToolsUtility.MODID;


public class ScreenSetup {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<TileEntityType<?>> TILES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, MODID);
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = new DeferredRegister<>(ForgeRegistries.CONTAINERS, MODID);

    public static void register() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<ScreenBlock> SCREEN = BLOCKS.register("screen", () -> new ScreenBlock(ScreenTileEntity::new, false));
    public static final RegistryObject<Item> SCREEN_ITEM = ITEMS.register("screen", () -> new BlockItem(SCREEN.get(), RFToolsUtility.createStandardProperties()));
    public static final RegistryObject<TileEntityType<?>> TYPE_SCREEN = TILES.register("screen", () -> TileEntityType.Builder.create(ScreenTileEntity::new, SCREEN.get()).build(null));

    public static final RegistryObject<ScreenBlock> CREATIVE_SCREEN = BLOCKS.register("creative_screen", () -> new ScreenBlock(CreativeScreenTileEntity::new, true));
    public static final RegistryObject<Item> CREATIVE_SCREEN_ITEM = ITEMS.register("creative_screen", () -> new BlockItem(CREATIVE_SCREEN.get(), RFToolsUtility.createStandardProperties()));
    public static final RegistryObject<TileEntityType<?>> TYPE_CREATIVE_SCREEN = TILES.register("creative_screen", () -> TileEntityType.Builder.create(CreativeScreenTileEntity::new, CREATIVE_SCREEN.get()).build(null));

    public static final RegistryObject<ContainerType<GenericContainer>> CONTAINER_SCREEN = CONTAINERS.register("screen", GenericContainer::createContainerType);

    public static final RegistryObject<ScreenHitBlock> SCREEN_HIT = BLOCKS.register("screen_hitblock", ScreenHitBlock::new);
    public static final RegistryObject<TileEntityType<?>> TYPE_SCREEN_HIT = TILES.register("screen_hitblock", () -> TileEntityType.Builder.create(ScreenHitTileEntity::new, SCREEN_HIT.get()).build(null));

    public static final RegistryObject<ScreenControllerBlock> SCREEN_CONTROLLER = BLOCKS.register("screen_controller", ScreenControllerBlock::new);
    public static final RegistryObject<Item> SCREEN_CONTROLLER_ITEM = ITEMS.register("screen_controller", () -> new BlockItem(SCREEN_CONTROLLER.get(), RFToolsUtility.createStandardProperties()));
    public static final RegistryObject<TileEntityType<?>> TYPE_SCREEN_CONTROLLER = TILES.register("screen_controller", () -> TileEntityType.Builder.create(ScreenControllerTileEntity::new, SCREEN_CONTROLLER.get()).build(null));
    public static final RegistryObject<ContainerType<GenericContainer>> CONTAINER_SCREEN_CONTROLLER = CONTAINERS.register("screen_controller", GenericContainer::createContainerType);

    public static final RegistryObject<Item> TEXT_MODULE = ITEMS.register("text_module", TextModuleItem::new);
    public static final RegistryObject<Item> ENERGY_MODULE = ITEMS.register("energy_module", EnergyModuleItem::new);
    public static final RegistryObject<Item> ENERGYPLUS_MODULE = ITEMS.register("energyplus_module", EnergyPlusModuleItem::new);
    public static final RegistryObject<Item> INVENTORY_MODULE = ITEMS.register("inventory_module", InventoryModuleItem::new);
    public static final RegistryObject<Item> INVENTORYPLUS_MODULE = ITEMS.register("inventoryplus_module", InventoryPlusModuleItem::new);
    public static final RegistryObject<Item> CLOCK_MODULE = ITEMS.register("clock_module", ClockModuleItem::new);
    public static final RegistryObject<Item> FLUID_MODULE = ITEMS.register("fluid_module", FluidModuleItem::new);
    public static final RegistryObject<Item> FLUIDPLUS_MODULE = ITEMS.register("fluidplus_module", FluidPlusModuleItem::new);
    public static final RegistryObject<Item> MACHINEINFORMATION_MODULE = ITEMS.register("machineinformation_module", MachineInformationModuleItem::new);
    public static final RegistryObject<Item> COMPUTER_MODULE = ITEMS.register("computer_module", ComputerModuleItem::new);
    public static final RegistryObject<Item> BUTTON_MODULE = ITEMS.register("button_module", ButtonModuleItem::new);
    public static final RegistryObject<Item> ELEVATOR_MODULE = ITEMS.register("elevator_button_module", ElevatorButtonModuleItem::new);
    public static final RegistryObject<Item> REDSTONE_MODULE = ITEMS.register("redstone_module", RedstoneModuleItem::new);
    public static final RegistryObject<Item> COUNTER_MODULE = ITEMS.register("counter_module", CounterModuleItem::new);
    public static final RegistryObject<Item> COUNTERPLUS_MODULE = ITEMS.register("counterplus_module", CounterPlusModuleItem::new);
    public static final RegistryObject<Item> STORAGECONTROL_MODULE = ITEMS.register("storage_control_module", StorageControlModuleItem::new);
    public static final RegistryObject<Item> DUMP_MODULE = ITEMS.register("dump_module", DumpModuleItem::new);

    public static void initClient() {
        SCREEN.get().initModel();
    }
}
