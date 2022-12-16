describe('Visit website', () => {
  it('Visits the website', () => {
    cy.visit('http://localhost:3000/products')
  })
})