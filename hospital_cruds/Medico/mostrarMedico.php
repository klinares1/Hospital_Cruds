<?php
include('../conexion.php');
$link=conectar();
$res=array();
$res['datos']=array();
$sql="select * from medico";
$resu=mysqli_query($link,$sql);
while($row=mysqli_fetch_array($resu)){
$i['CC_Medico'] = $row[0];
$i['Nombre_Medico'] = $row[1];
$i['Apellido_Medico'] = $row[2];
$i['Id_Genero'] = $row[3];
$i['Id_Especialidad'] = $row[4];
$i['Contraseña'] = $row[5];
array_push($res['datos'],$i);
}
$res["succes"]="1";
echo json_encode($res);
mysqli_close($link);
?>