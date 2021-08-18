# Percolation
A system is modeled using an n x n grid consisting initially of all blocked cells. Cells can be blocked, open(empty), or open(full) = connected to the top row. The system percolates once a connection is made between a cell in the top row and a cell in the bottom row. 

Cells are randomly opened until the system percolates (Monte Carlo method). The number of open cells compared to blocked cells at percolation represents the conditions needed for one to pass through the other (ex. water passing through a porrus material). Sorry for the vague/weak explanation in the last sentence, this model simply has many differing use cases.

PercolationStats.java runs x amount of trials on an n x n grid yielding the mean(percolation threshold), the standard deviation(threshold sharpness), and the 95% confidence interval for said threshold.

![image](https://user-images.githubusercontent.com/84862652/129913336-ec5d32e0-b0b1-478b-a438-3ee7077346d7.png)
![image](https://user-images.githubusercontent.com/84862652/129913371-a0c5a9cb-d1d6-4b82-a83a-8e088fbf7fa6.png)
