/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report_galerie.src.IServices;

import java.sql.SQLException;
import java.util.List;
import report_galerie.src.Entites.Feedback;

/**
 *
 * @author narug
 */
public interface IServicesAdminFeedbacks {
     public List<Feedback> ShowFeedback() throws SQLException;
}
