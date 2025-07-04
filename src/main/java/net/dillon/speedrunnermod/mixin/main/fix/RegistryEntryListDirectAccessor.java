package net.dillon.speedrunnermod.mixin.main.fix;

import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;
import java.util.Set;

@Author(Authors.CECH12)
@Mixin(RegistryEntryList.Direct.class)
public interface RegistryEntryListDirectAccessor<T> {
    @Accessor
    List<RegistryEntry<T>> getEntries();

    @Accessor @Final @Mutable
    void setEntries(List<RegistryEntry<T>> var1);

    @Accessor
    void setEntrySet(Set<RegistryEntry<T>> var1);
}