package dev.andante.audience.mixin;

import dev.andante.audience.Audience;
import dev.andante.audience.player.PlayerReference;
import dev.andante.audience.player.PlayerSet;
import dev.andante.audience.player.StandalonePlayerReference;
import net.minecraft.server.PlayerConfigEntry;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("AddedMixinMembersNamePattern")
@Mixin(PlayerConfigEntry.class)
public abstract class PlayerConfigEntryMixin implements Audience, PlayerReference {
    @Override
    public @NotNull PlayerSet getAudiencePlayers() {
        StandalonePlayerReference reference = this.getHardReference();
        return new PlayerSet(List.of(reference));
    }

    @Override
    public @NotNull UUID getReferenceUuid() {
        PlayerConfigEntry that = (PlayerConfigEntry) (Object) this;
        return that.id();
    }

    @Override
    public boolean equals(Object other) {
        if (super.equals(other)) {
            return true;
        }

        if (other instanceof PlayerReference reference) {
            return reference.getReferenceUuid() == this.getReferenceUuid();
        }

        return false;
    }
}
