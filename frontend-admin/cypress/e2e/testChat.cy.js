describe('testChat', () => {
    var dayOfTheWeek = (new Date).getDay(); //1 = monday

    if (dayOfTheWeek == 1) {
        it("will act as though it's an admin with orange socks on monday", () => {
            cy.visit('http://localhost:3002/chat')
      
            cy.get('.register > input').type('admin');
            cy.get('.register > button').click();
            cy.get('.send-message > input').type('oranje');
            cy.get('.send-message > button').click();
          
            cy.get('.message-data').eq(1).should('have.text', 'Admin ✔\nOranje sokken ✔\nMaandag ✔\n');
        })
        it("will act as though it's an admin with blue socks on monday", () => {
            cy.visit('http://localhost:3002/chat')
      
            cy.get('.register > input').type('admin');
            cy.get('.register > button').click();
            cy.get('.send-message > input').type('blauw');
            cy.get('.send-message > button').click();
          
            cy.get('.message-data').eq(1).should('have.text', 'Admin ✔\nOranje sokken ✘\nMaandag ✔\n');  
        })
    } else {
        it("will act as though it's an admin with orange socks on another day than monday", () => {
            cy.visit('http://localhost:3002/chat')
        
            cy.get('.register > input').type('admin');
            cy.get('.register > button').click();
            cy.get('.send-message > input').type('oranje');
            cy.get('.send-message > button').click();
            
            cy.get('.message-data').eq(1).should('have.text', 'Admin ✔\nOranje sokken ✔\nMaandag ✘\n');  
        })

        it("will act as though it's an admin with blue socks on another day than monday", () => {
            cy.visit('http://localhost:3002/chat')
        
            cy.get('.register > input').type('admin');
            cy.get('.register > button').click();
            cy.get('.send-message > input').type('blauw');
            cy.get('.send-message > button').click();
            
            cy.get('.message-data').eq(1).should('have.text', 'Admin ✔\nOranje sokken ✘\nMaandag ✘\n');  
        })
    }
    it("will act as though it isn't an admin", () => {
        cy.visit('http://localhost:3002/chat')
  
        cy.get('.register > input').type('not admin');
        cy.get('.register > button').click();
      
        cy.get('.message-data').should('not.exist');  
    })
  })