package net.dillon.speedrunnermod.mixin.main.fix;

import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;
import java.util.Set;

@Author(Authors.CECH12)
@Mixin(HolderSet.Direct.class)
public interface RegistryEntryListDirectAccessor<T> {
    @Accessor
    List<Holder<T>> getContents();
    @Accessor @Final @Mutable
    void setContents(List<Holder<T>> var1);
    @Accessor
    void setContentsSet(Set<Holder<T>> var1);
}