/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entites.Galerie;
import Entites.Report;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author narug
 */
public interface IServicesReport {
    public List<Report> ShowReport() throws SQLException;
}
