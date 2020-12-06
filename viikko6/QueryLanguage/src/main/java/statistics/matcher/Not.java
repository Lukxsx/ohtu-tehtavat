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
public class Not implements Matcher {
    
    private Matcher m;
    
    public Not(Matcher m) {
        this.m = m;
    }

    @Override
    public boolean matches(Player p) {
        return !(this.m.matches(p));
    }
    
}
