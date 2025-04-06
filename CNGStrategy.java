/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy;

/**
 *
 * @author phaml
 */
public class CNGStrategy implements EfficiencyStrategy {
    @Override
    public double calculate(double fuel_consumed, double mileage) {
        return (mileage / fuel_consumed) *0.6;
    }
}
