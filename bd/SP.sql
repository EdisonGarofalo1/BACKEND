
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_rolfindAll`()
BEGIN

   
       SELECT DISTINCT r.id_rol, r.rol_name, rop.id_opcion, rop.nombre_opcion 
    FROM rol r 
    INNER JOIN rol_rol_opciones op ON r.id_rol = op.rol_id_rol 
    INNER JOIN rol_opciones rop ON rop.id_opcion = op.rol_opciones_id_opcion
    order by r.id_rol, r.rol_name, rop.id_opcion, rop.nombre_opcion ASC;
END ;;
DELIMITER ;
 

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_rolfindById`(in p_id_rol int)
BEGIN
select r.id_rol,r.rol_name,rop.id_opcion,rop.nombre_opcion from rol r inner join rol_rol_opciones op on  r.id_rol=op.rol_id_rol inner join 
   rol_opciones rop on rop.id_opcion= op.rol_opciones_id_opcion where r.id_rol=p_id_rol;

END ;;
           
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_rol_save`(
IN p_id_rol int , p_rolName VARCHAR(50),
    IN p_opcionesList JSON)
BEGIN
DECLARE done INT DEFAULT FALSE;
   DECLARE J_mensaje varchar(100);
    DECLARE J_idOpcion INT;
    DECLARE idx INT DEFAULT 0;
 
 
 
    -- Crear tabla temporal para almacenar opciones
    CREATE TEMPORARY TABLE tmp_opcione (
        idOpcion INT
    );

    -- Insertar opciones no nulas en la tabla temporal
    WHILE NOT done DO
        SET J_idOpcion = CAST(JSON_UNQUOTE(JSON_EXTRACT(p_opcionesList, CONCAT('$[', idx, '].idOpcion'))) AS UNSIGNED);
        IF J_idOpcion IS NOT NULL THEN
            INSERT INTO tmp_opcione (idOpcion) VALUES (J_idOpcion);
        END IF;
        SET idx = idx + 1;
        IF JSON_LENGTH(p_opcionesList) <= idx THEN
            SET done = TRUE;
        END IF;
    END WHILE;
 
 
 
 
 
 
    IF p_id_rol IS NOT NULL  THEN
       IF EXISTS (SELECT 1 FROM rol WHERE id_rol = p_id_rol) THEN
        -- Actualizar el nombre del rol
          update  rol set  rol_name=p_rolName
    where   id_rol=p_id_rol;

    -- Eliminar las relaciones anteriores del rol con las opciones
    DELETE FROM rol_rol_opciones WHERE rol_id_rol = p_id_rol;
    
     -- Insertar las asociaciones no nulas en la tabla de opciones
    INSERT INTO rol_rol_opciones (rol_opciones_id_opcion, rol_id_rol)
    SELECT idOpcion, p_id_rol FROM tmp_opcione WHERE idOpcion IS NOT NULL;

    
     
    -- Limpiar la tabla temporal
   DROP TEMPORARY TABLE IF EXISTS tmp_opcione;
      SET J_mensaje = CONCAT('rol actualizado exitosamente con ID: ', p_id_rol); 
    
    else
    
     SET J_mensaje = CONCAT('rol no encontrado  con  es ID: ', p_id_rol); 
    
    
    end if;
      
    ELSE
    
    -- Insertar el rol y obtener su ID
    INSERT INTO rol (rol_name) VALUES (p_rolName);
    SET @lastIdRol = LAST_INSERT_ID();

    -- Insertar las asociaciones no nulas en la tabla de opciones
    INSERT INTO rol_rol_opciones (rol_opciones_id_opcion, rol_id_rol)
    -- SELECT idOpcion, @lastIdRol FROM tmp_opcione;
     SELECT idOpcion, @lastIdRol FROM tmp_opcione WHERE idOpcion IS NOT NULL;

    -- Eliminar tabla temporal
    DROP TEMPORARY TABLE IF EXISTS tmp_opcione;


        
          SET J_mensaje = CONCAT('rol guardado exitosamente con ID: ', @lastIdRol);
    END IF;
 
 
 

 
 
 select  J_mensaje;

END ;;
DELIMITER ;

