<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <link rel="stylesheet" href="style.css">

    <!--script to include JQuery (from JQuery CDN)-->
    <script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>


</head>
<body>
   
    <div id="wrapper"> 
        <h1>Welcome <?php session_start(); echo $_SESSION['username']; ?> to my Website</h1>
    
        <div class="chat_wrapper">
            <div id="chat"></div>
            
                <form method="POST" id="messageForm">                
                    <textarea name="message" cols="30" rows="7" class="textarea"></textarea>
                </form>
            
            
        </div>    
    </div>

    <script>
    // call LoadChat() to display past messages in chat window
    LoadChat();

    // to auto populate data after every 1 second (1000 milisecond)
    setInterval(function(){
        LoadChat();
    }, 1000);


        // part to handle text input
        $('.textarea').keyup(function(e){ // keyup checks keys pressed
            // if 'enter' pressed, text is submitted
            if( e.which == 13 ){           // 13 is ascii for 'enter'
                $('form').submit();
            }
        });
        // whenever form is submitted, call a function to submit it with JQuery
        $('form').submit(function(){
        // save message to the db
            // post message to a php page
            var message = $('.textarea').val();
            // variable to store message to be sent
            // ('page where we want to send the message + message', function for the response)
            $.post('handlers/messages.php?action=sendMessage&message=' +message, function(response){
                
                // if response from messages.php is 1 (== data inserted to db), reset textarea form
                if( response==1 ){
                    // call LoadChat() to refresh chat windoe with current message
                    LoadChat();
                    // $('form').reset();   // this way it does not work, need
                    document.getElementById('messageForm').reset();
                }
            });
            //page wont be refreshed if return false
            return false;
        });

        // function to load and display past messages
        function LoadChat()
        {
            // post a request to messages.php to get all past messages, callback function will catch the response
            $.post('handlers/messages.php?action=getMessages', function(response){
                
                // variables to set up scrolling the screen
                // check what is the current scroll position
                var scrollpos = $('#chat').scrollTop();  // when loaded, end of chat is current pos
                // current scroll position + #chat div height
                var scrollpos = parseInt(scrollpos) + 320; // current pos + (300 for chat window height - in css + 2*10 padding)
                // fetching the chat div scroll height with the property
                var scrollHeight = $('#chat').prop('scrollHeight');

                // adding the respose to the chat window
                $('#chat').html(response);

                // check if scroll position is less than scroll height, then don't run anithing
                if(scrollpos < scrollHeight){

                } else{
                // auto scroll to the bottom of the chat (to the newest)
                $('#chat').scrollTop( $('#chat' ).prop('scrollHeight') );
                }

            });
        }



    </script>

</body>
</html>