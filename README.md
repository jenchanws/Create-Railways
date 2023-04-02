
<h1 align="center">Create Steam 'n Rails</h1>
<h2 align="center">This was Razziel messing around, and should NOT be merged. I repeat. It should NOT be merged.</h2>
<br><br>
<img src="https://discordapp.com/api/guilds/706277846389227612/widget.png?style=banner3" alt="Discord Banner 3" align="right"/>

**Create Steam 'n Rails** is an addon mod for Create that aims to extend Create's train and steam systems. Current features include custom tracks, semaphores, and conductors.

## Contributing (for team members):
1. Create a new branch for your feature (named `1.18/<feat>`)
2. Write your feature
3. Make a pull request
4. Have somebody review it, and merge

### Datagen (if runData fails):
Can occasionally have some bugs, see [here](src/main/java/com/railwayteam/railways/mixin/README.md) for more info. (There should be an upstream Create fix for this, but that is not yet in any 1.18 release, and so we can't take advantage of it.)

### Commit Tricks:

- Include `[ci skip]` in your commit message to skip the automatic preview build
you can use this for example if the change you made is very minor, and not worth
a preview, or if you are just fixing a typo in the README, etc.

### FIXME:

![](screenshots/hanging_bogey_bug.png)
hanging bogeys on slopes/curves are wrong: Fix bogey's anchor pt

specifically: `TravellingPoint#getPositionWithOffset`,

look at `edge.getNormal(node1, node2, t).scale(1)`, maybe replace
`scale(1)` with `scale(-1)`

need to:
- store bogey type in TravellingPoint
- change rendering offsets for:
  - assembly
  - disassembly
  - hanging bogeys
