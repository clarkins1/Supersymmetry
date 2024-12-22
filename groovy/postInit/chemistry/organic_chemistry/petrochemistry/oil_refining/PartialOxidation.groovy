PYROLYSE = recipemap('pyrolyse_oven')

public static class PartialOxidationFeedstock {
    String name
    int steam_consumed
    int oxygen_consumed
    int gas_produced
    int duration

    PartialOxidationFeedstock(String name, int steam_consumed, int oxygen_consumed, int gas_produced, int duration) {
        this.name = name
        this.steam_consumed = steam_consumed
        this.oxygen_consumed = oxygen_consumed
        this.gas_produced = gas_produced
        this.duration = duration
    }
}

public static feedstocks = [
    light_naphtha : new PartialOxidationFeedstock('light_naphtha', 530, 4970, 17505, 35),
    naphtha : new PartialOxidationFeedstock('naphtha', 500, 6000, 20500, 41),
    heavy_naphtha : new PartialOxidationFeedstock('heavy_naphtha', 620, 8375, 28015, 56),
    kerosene : new PartialOxidationFeedstock('kerosene', 345, 11155, 35490, 71),
    light_gas_oil : new PartialOxidationFeedstock('light_gas_oil', 1480, 14020, 47670, 95),
    heavy_gas_oil : new PartialOxidationFeedstock('heavy_gas_oil', 4120, 15880, 62235, 124),
    atmospheric_residue : new PartialOxidationFeedstock('atmospheric_residue', 8600, 31400, 123865, 248),
    vacuum_residue : new PartialOxidationFeedstock('vacuum_residue', 20990, 24010, 149035, 298),
]

for (feedstock in feedstocks.values()) {
    PYROLYSE.recipeBuilder()
        .fluidInputs(fluid('dense_steam') * feedstock.steam_consumed)
        .fluidInputs(fluid('oxygen') * feedstock.oxygen_consumed)
        .fluidInputs(fluid(feedstock.name) * 1000)
        .fluidOutputs(fluid('monoxide_rich_syngas') * feedstock.gas_produced)
        .duration(feedstock.duration)
        .EUt(120)
        .buildAndRegister()
}