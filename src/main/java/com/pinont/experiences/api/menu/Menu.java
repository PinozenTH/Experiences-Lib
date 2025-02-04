package com.pinont.experiences.api.menu;

import com.pinont.experiences.plugin.ExpPlugin;
import com.pinont.experiences.api.utils.Common;
import com.pinont.experiences.api.utils.enums.MenuSize;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.metadata.FixedMetadataValue;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Menu {

	private final List<Button> buttons = new ArrayList<>();
	private final List<Props> props = new ArrayList<>();

	@Setter
	private int size = 9 * 3;

	@Setter
	private String title = "Custom Menu";

	private final Menu parent;

	public Menu() {
		this(null);
	}

	public Menu(@Nullable Menu parent) {
		this.parent = parent;
	}

	public final List<Button> getButtons() {
		return buttons;
	}
	public final List<Props> getProps() {
		return props;
	}

	protected final void addButton(Button button) {
		this.buttons.add(button);
	}
	protected final void addProp(Props prop) {
		this.props.add(prop);
	}

	public final void displayTo(Player player) {
		final Inventory inventory = Bukkit.createInventory(player, this.size,
				Common.colorize(this.title));

		for (final Button button : this.buttons)
			inventory.setItem(button.getSlot(), button.getItem());
		for (final Props prop : this.props)
			for (int slot : prop.getSlot())
				inventory.setItem(slot, prop.getItem());

		if (player.hasMetadata("PiXLibMenu"))
			player.closeInventory();

		player.setMetadata("PiXLibMenu", new FixedMetadataValue(ExpPlugin.getPlugin(), this));

		player.openInventory(inventory);
	}

	public final void displayNew(Player player, Menu menu) {
		final Inventory inventory = player.getOpenInventory().getTopInventory();
		inventory.clear();
		for (final Button button : menu.buttons)
			inventory.setItem(button.getSlot(), button.getItem());
		for (final Props prop : menu.props)
			for (int slot : prop.getSlot())
				inventory.setItem(slot, prop.getItem());
	}
}
