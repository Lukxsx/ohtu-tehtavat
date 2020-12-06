/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.matcher;

import statistics.Player;

/**
 *
 * @author luks
 */
public class HasFewerThan implements Matcher {
    private HasAtLeast hasAtLeast;
    
    public HasFewerThan(int value, String category) {
        this.hasAtLeast = new HasAtLeast(value, category);
    }

    @Override
    public boolean matches(Player p) {
        return !(hasAtLeast.matches(p));
    }
}
