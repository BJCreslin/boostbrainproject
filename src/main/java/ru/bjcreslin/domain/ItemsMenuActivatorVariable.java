package ru.bjcreslin.domain;

import lombok.Data;
import org.springframework.ui.Model;

/**
 * Класс хранения данных доступа к пунктам меню.
 * canAnalyse  - Можно ли анализировать (есть данные в БД?)
 * canSaveDisk - Можно ли записывать кеш на диск (Есть ли данные в кеши памяти)
 * canLoadDisk - Можно ли загрузить данные с диска ( есть ли файл rows.zip)
 */
@Data
public class ItemsMenuActivatorVariable {
    private static boolean isCanAnalyse() {
        return canAnalyse;
    }

    public static void setCanAnalyse(boolean canAnalyse) {
        ItemsMenuActivatorVariable.canAnalyse = canAnalyse;
    }

    private static boolean isCanSaveDisk() {
        return canSaveDisk;
    }

    public static void setCanSaveDisk(boolean canSaveDisk) {
        ItemsMenuActivatorVariable.canSaveDisk = canSaveDisk;
    }

    private static boolean isCanLoadDisk() {
        return canLoadDisk;
    }

    public static void setCanLoadDisk(boolean canLoadDisk) {
        ItemsMenuActivatorVariable.canLoadDisk = canLoadDisk;
    }

    private static boolean canAnalyse = false;
    private static boolean canSaveDisk = false;
    private static boolean canLoadDisk = false;

    public static void itemMenuEnabled(Model model) {
        model.addAttribute("isCanLoadDisk", ItemsMenuActivatorVariable.isCanLoadDisk());
        model.addAttribute("isCanSaveDisk", ItemsMenuActivatorVariable.isCanSaveDisk());
        model.addAttribute("isAnalise", ItemsMenuActivatorVariable.isCanAnalyse());
    }

}
