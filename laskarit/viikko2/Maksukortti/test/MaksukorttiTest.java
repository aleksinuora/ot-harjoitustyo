/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.*;
import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author aleksi
 */
public class MaksukorttiTest {
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }    
    
     @Test
     public void konstruktoriAsettaaSaldonOikein() {
         kortti = new Maksukortti(10);
         Assert.assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
     }
     
     @Test
     public void syoEdullisestiVahentaaSaldoaOikein() {
         kortti.syoEdullisesti();
         Assert.assertEquals("Kortilla on rahaa 7.5 euroa", kortti.toString());
     }
     
     @Test
     public void syoMaukkaastiVahentaaSaldoaOikein() {
         kortti.syoMaukkaasti();
         Assert.assertEquals("Kortilla on rahaa 6.0 euroa", kortti.toString());
     }
     
    @Test
    public void syoEdullisestiEiVieSaldoaNegatiiviseksi() {
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        kortti.syoEdullisesti();
        Assert.assertEquals("Kortilla on rahaa 2.0 euroa", kortti.toString());
    }

    @Test
    public void kortilleVoiLadataRahaa() {
        kortti.lataaRahaa(25);
        Assert.assertEquals("Kortilla on rahaa 35.0 euroa", kortti.toString());
    }

    @Test
    public void kortinSaldoEiYlitaMaksimiarvoa() {
        kortti.lataaRahaa(200);
        Assert.assertEquals("Kortilla on rahaa 150.0 euroa", kortti.toString());
    }    
}
