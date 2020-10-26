import React from 'react';

function Comment({commentary}){

       

    return(
        <div className="commentary">
            <p>Author: {commentary.author.name}</p>
            <p>{commentary.body}</p>
        </div>
    )

}

export default Comment;