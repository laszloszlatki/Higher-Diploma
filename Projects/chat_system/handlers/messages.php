<?php

include('../config.php');

    // check the request action comming from any page (index.php now)
    switch( $_REQUEST['action'] ){

        case "sendMessage":
            
            session_start();

            // insert user and message to db (first prepare values the execute)
            $query = $db->prepare("INSERT INTO messages SET name=?, message=?");
            // we pass the value of the message as an array (coming from index.php $.post message variable)
            $run = $query->execute([$_SESSION['username'], $_REQUEST['message']]);
        
            // if line successfuly inserted to db, return 1. This is used in index.php to reset textarea to blank 
            if( $run ){
                echo 1;
                exit;
            }

        break;

        case "getMessages":

            //get all message from db to be displayed
            $query = $db->prepare("SELECT * FROM messages");
            // execute query
            $run = $query->execute();
            // // fetch all results
            // $rs = $query->fetchAll();

            // fetch all results (as objects)
            $rs = $query->fetchAll(PDO::FETCH_OBJ);

            // create chat variable to hold the chat - all messages and names
            $chat = '';
            // loop through result, append name and message to schat variable
            foreach( $rs as $message){
                
                if($message->name == $_SESSION['username']){
                    $chat .= '<div class="my-message">
                                <strong>'.$message->name.'</strong> '.$message->message.'    
                                <span>'.date('d-m-Y h:i a', strtotime($message->date)).'</span>
                                </div>';
                }else{

                    $chat .= '<div class="notMy-message">
                                <strong>'.$message->name.'</strong> '.$message->message.'    
                                <span>'.date('d-m-Y h:i a', strtotime($message->date)).'</span>
                                </div>';
                }
                
                
            }

            echo $chat;

        break;

    }

?>